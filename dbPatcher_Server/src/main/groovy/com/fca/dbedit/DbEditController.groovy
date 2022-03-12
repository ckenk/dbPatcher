package com.fca.dbedit

import com.fca.dbedit.data.JdbcRepository
import com.fca.dbedit.entity.Bus
import com.fca.dbedit.entity.EngVehicleConfig
import com.fca.dbedit.entity.VarVer
import com.fca.dbedit.entity.YbInfo
import com.fca.dbedit.exception.EngVehicleConfigUpdateFailedException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.util.FileCopyUtils
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.support.RedirectAttributes

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.lang.invoke.MethodHandles

/**
 * Created by kkulathilake on 5/9/2016.
 */

@RestController
@Scope("request")
class DbEditController {

    @Autowired
    private JdbcRepository repository

    @Autowired
    private DbData dbData

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }


//    CORS pre-flight calls land here
//    @RequestMapping(method = RequestMethod.OPTIONS, value="/**")
//    public void manageOptions(HttpServletResponse response)
//    {
//        println("********************  OPTIONS RECEIVED ********************")
//    }

    @RequestMapping(method = RequestMethod.GET, value = "/global", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<InputStreamResource> sendGlobalDB() throws IOException {
        ClassPathResource glDB = new ClassPathResource(dbData.GLOBAL_DB);
        FileSystemResource glDBF = new FileSystemResource(dbData.GLOBAL_DB);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        headers.add("Content-Disposition", "attachment; filename=global.db");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(glDBF.contentLength())
                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .body(new InputStreamResource(glDBF.getInputStream()));
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.POST, value = "/global", produces = "application/json")
    public Map<String, String> handleGlobalDbUpload(@RequestParam("file") MultipartFile[] files,
                                                    RedirectAttributes redirectAttributes,
                                                    HttpServletRequest request) {
        LOGGER.info("request = ${request}")
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    dbData.GLOBAL_DB = dbData.GLOBAL_DB_DIR + "/" + file.originalFilename
                    LOGGER.info("***********  Saving ${file.originalFilename}")
                    BufferedOutputStream stream = new BufferedOutputStream(
                            new FileOutputStream(new File(dbData.GLOBAL_DB)));
                    FileCopyUtils.copy(file.getInputStream(), stream);
                    stream.close();
                }
                catch (Exception e) {
                    LOGGER.info(e.message)
                }
            } else {
                LOGGER.info("failed to upload global.db because the file was empty");
            }
        }
        return ["upload": "success"];
    }

    @RequestMapping(method = RequestMethod.POST, value = "/ecus", produces = "application/json")
    public Map<String, String> handleEcuDbUpload(@RequestParam(value = "files", required = false) MultipartFile[] files,
                                                 RedirectAttributes redirectAttributes,
                                                 HttpServletRequest request) {
        LOGGER.info("request = ${request}")

        File ecus_db_dir = new File(dbData.ECUS_DB_DIR)

        for (MultipartFile tfile : files) {
            if (!tfile.isEmpty()) {
                try {
                    LOGGER.info("***********  Saving ${tfile.originalFilename}")
                    BufferedOutputStream stream = new BufferedOutputStream(
                            new FileOutputStream(new File(ecus_db_dir.absolutePath + "/" + tfile.originalFilename)));
                    FileCopyUtils.copy(tfile.getInputStream(), stream);
                    stream.close();

                    String file_name_wo_ext = tfile.originalFilename.split(/\./)[0]
                    dbData.ECU_DBS << [(file_name_wo_ext): null]
                    redirectAttributes.addFlashAttribute("message",
                            "You successfully uploaded " + tfile.originalFilename + "!");
                }
                catch (Exception e) {
                    LOGGER.info(e.message)
                    redirectAttributes.addFlashAttribute("message",
                            "You failed to upload " + tfile.originalFilename + " => " + e.getMessage());
                }
            } else {
                redirectAttributes.addFlashAttribute("message",
                        "You failed to upload " + tfile.originalFilename + " because the file was empty");
            }
        }
        return ["upload": "success"];
    }

    @RequestMapping(method = RequestMethod.GET, value = "/ecus", produces = "application/json")
    public List<String> getEcuDb() {
        LOGGER.info("dbData instanceof = " + (dbData.getClass().toString()))
        return repository.ecuDbs()
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET, value = "/yb", produces = "application/json")
    public List<YbInfo> getAllYearBody() {
        return repository.allYBs(dbData)
    }

    @RequestMapping(method = RequestMethod.GET, value = "/global/{year}/{body}", produces = "application/json")
    public List<EngVehicleConfig> getEngVehConfigsByYb(@PathVariable String year, @PathVariable String body) {
        LOGGER.info("${year}, ${body}")
        def data = repository.engVehConfigsByYb(year, body, dbData)
        LOGGER.info("EngVehicleConfigs = ${data}")
        return data
    }

    @RequestMapping(method = RequestMethod.GET, value = "/yb/{year}/{body}", produces = "application/json")
    public List<YbInfo> getYearBody(@PathVariable String year, @PathVariable String body) {
        return repository.yb(dbData, new YbInfo(year, body))
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{ecu_db_name}/var_ver", produces = "application/json")
    public List<VarVer> getEcuVarVer(@PathVariable String ecu_db_name) {
        LOGGER.info("dbData instanceof = " + (dbData.getClass().toString()))
        return repository.ecuVarVer(ecu_db_name, dbData)
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{ecu_db_name}/bus", produces = "application/json")
    public List<VarVer> getEcuBus(@PathVariable String ecu_db_name) {
        LOGGER.info("dbData instanceof = " + (dbData.getClass().toString()))
        return repository.ecuBus(ecu_db_name, dbData)
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/global/{eng_veh_conf_id}", produces = "application/json")
    public List<VarVer> updateVarVerInGlobal(@PathVariable String eng_veh_conf_id,
                                             @RequestParam String ecu_db_name, @RequestParam String var_ver_id) {
        return repository.updateVarVerInGlobal(eng_veh_conf_id, ecu_db_name, var_ver_id, dbData)
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/global/{eng_veh_conf_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> deleteEngVehConf(@PathVariable Integer eng_veh_conf_id) {
        def result = repository.deleteEngVehConf(eng_veh_conf_id, dbData)
        if (result == 1) {
            return new ResponseEntity<Map<String, String>>(["result": "success"], HttpStatus.OK);
        };
        LOGGER.info("Delete failed: ${eng_veh_conf_id}, ${result}")
        return new ResponseEntity<Map<String, String>>(["result": "failed"], HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/global/{year}/{body}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Map<String, String>> insertVarVerInToGlobal(
            @PathVariable String year, @PathVariable String body,
            @RequestParam String ecu_db_name, @RequestParam String ecu_var_ver_id, @RequestParam(required = false) Boolean new_entry,
            @RequestParam(required = false) String hexXmitString, @RequestParam(required = false) Integer gatewayBitPos,
            @RequestParam(required = false) Integer busId, @RequestParam(required = false) String canbitsize) {
        def newEntryMap = ["hexXmitString":hexXmitString, "gatewayBitPos":gatewayBitPos, "busId":busId, "canbitsize":canbitsize]
        def result = repository.insertNewVarVerInToGlobal(year, body, ecu_db_name, ecu_var_ver_id, new_entry, newEntryMap, dbData)
        return new ResponseEntity<Map<String, String>>(["result": result], HttpStatus.OK)
    }

    @RequestMapping(method = RequestMethod.GET, value = "/global/bus", produces = "application/json")
    public List<Bus> engBuses() {
        return repository.engBuses(dbData)
    }

    @RequestMapping(method = RequestMethod.GET, value = "/init", produces = "application/json")
    public ResponseEntity<Map<String, String>> init() {
        File ecus_db_dir = new File(dbData.ECUS_DB_DIR)
        File gl_db_dir = new File(dbData.GLOBAL_DB_DIR)
        if(gl_db_dir.exists() && !gl_db_dir.deleteDir()) {
            throw new EngVehicleConfigUpdateFailedException("Failed to delete Global DB folder")
        }
        Thread.sleep(500)
        if(!gl_db_dir.mkdirs()) {
            throw new EngVehicleConfigUpdateFailedException("Failed to create Global DB folder")
        }
        if(ecus_db_dir.exists() && !ecus_db_dir.deleteDir()) {
            throw new EngVehicleConfigUpdateFailedException("Failed to delete ECU DB folder")
        }
        Thread.sleep(1500)
        if(!ecus_db_dir.mkdirs()) {
            throw new EngVehicleConfigUpdateFailedException("Failed to create ECU DB folder")
        }
    }

}
