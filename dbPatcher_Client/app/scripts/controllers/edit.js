'use strict';

/**
 * @ngdoc function
 * @name dbeditClientApp.controller:EditCtrl
 * @description
 * # EditCtrl
 * Controller of the dbeditClientApp
 */
var app = angular.module('dbeditClientApp');

app.controller('EditCtrl', ['$scope', '$http', '$location', '$document', '$filter', 'urlBuilder', '$parse','$q',
						   function ($scope, $http, $location, $document, $filter, urlBuilder, $parse, $q) {
		this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
							   
	$scope.status = "";
	$scope.selectedYb = 'all';
	$scope.ecuVarVerCollection = [];
	$scope.var_ver_selected = [];
	$scope.var_ver_selected_new_entry = [];
	$scope.varver_insert_status = {label:"", value:""};
	$scope.new_entry_params = [];
	$scope.searchText = "";
	$scope.ecu_buses = new Map(); //<db_name, ecu_buses>
							   
	var init_fields = function() {
		$scope.selectedYb = 'all';
		$scope.varver_insert_status.value = "";
		$scope.var_ver_selected = [];
		$scope.var_ver_selected_new_entry = [];
	};
							   
	$scope.checkSelectedYb = function() {
		if($scope.searchText === "" || $scope.searchText === null || $scope.searchText === undefined) {
			init_fields();
		}
	};
							   
	$scope.insertEcuVarVerToGlobal = function() {
		$scope.varver_insert_status.value = "";
		
		angular.forEach($scope.var_ver_selected_new_entry, function(ecu_var_ver) {
			var xmit = $scope.new_entry_params["xmit_"+ecu_var_ver.id];
			var gwbitpos = $scope.new_entry_params["gwbitpos_"+ecu_var_ver.id];
			var bus = $scope.new_entry_params["bus_" + ecu_var_ver.id];
						
			if(bus === null) { 
				$scope.varver_insert_status.value = "Missing bus for " + ecu_var_ver.file_name;
				return false;
			}
			if(ecu_var_ver.canbitsize === null) {
				$scope.varver_insert_status.value = "CAN ID bit size not selected " + ecu_var_ver.file_name;
				return false;
			}
			var busId = bus.id;
			var url = urlBuilder($scope.BASE_URL+"/global/"+$scope.selectedYb.year+"/"+$scope.selectedYb.body, 
								 {ecu_db_name:ecu_var_ver.file_name, ecu_var_ver_id:ecu_var_ver.id, new_entry:true, hexXmitString:xmit, gatewayBitPos:gwbitpos, 
								  busId:busId, canbitsize:ecu_var_ver.canbitsize});
			console.log("VarVer Insert URL", url);
			$http.post(url).success(insertEcuVarVerToFlobalSuccess).error(insertEcuVarVerToFlobalFailed);
		});
		angular.forEach($scope.var_ver_selected, function(ecu_var_ver) {
			var url = urlBuilder($scope.BASE_URL+"/global/"+$scope.selectedYb.year+"/"+$scope.selectedYb.body, 
								 {ecu_db_name:ecu_var_ver.file_name, ecu_var_ver_id:ecu_var_ver.id, new_entry:false, canbitsize:ecu_var_ver.canbitsize});
			console.log("VarVer Insert URL", url);
			$http.post(url).success(insertEcuVarVerToFlobalSuccess).error(insertEcuVarVerToFlobalFailed);
		});
	};

	function insertEcuVarVerToFlobalSuccess(response) {
		$scope.showEngVehConfigs($scope.selectedYb);
		console.log("Vehicle File update succeed", response);
		$scope.varver_insert_status.value = "Vehicle File update succeed";
	}

	function insertEcuVarVerToFlobalFailed(response) {
		console.log("Vehicle File update failed", response);
		if(response)
			$scope.varver_insert_status.value = "Vehicle File update failed: " + response.code + "|" + response.message;
		else
			$scope.varver_insert_status.value = "Vehicle File update failed";
	}
							   
	$scope.deleteEngVehConf = function(evc) {
		$http.delete($scope.BASE_URL + "/global/" + evc.id)
		.success(function(data){
			console.log("Eng Veh Config row deleted", data);
			var index = $scope.evcCollection.indexOf(evc);
			$scope.evcCollection.splice(index, 1);
			$scope.loadEcuPC();
		}).error(function(data) {
			console.log("Eng Veh Config row deletion failed", data);
		});
	};
							   
	$scope.loadYbs = function () {
		$http.get($scope.BASE_URL + "/yb")
		.success(function (data) {
			$scope.ybCollection = data;
			$http.get($scope.BASE_URL + "/global/bus")
				.success(function (busses) {
					$scope.busses = busses;
				}).error(function (bErr) {
					console.log("Error loading buses", bErr);
				});
		}).error(function (data) {
			$scope.status = data;
			console.log("Error in get /yb", data);
		});
	};

	$scope.loadYbs();
	$scope.isShown = function(yb) {
  		if ($scope.selectedYb === 'all') {
    		return true;
  		}
		return $scope.selectedYb === yb;
	};


	$scope.loadEcuPC = function() {
		$scope.ecuVarVerCollection = [];
		$http.get($scope.BASE_URL + "/ecus")
		.success(function(ecu_dbs) {
			console.log(ecu_dbs);
			angular.forEach(ecu_dbs, function(dbname) {
				
				$http.get($scope.BASE_URL + "/" + dbname + "/bus")
				.success(function (buses) {
					console.log("buses", buses);
					$scope.ecu_buses.set(dbname, buses);
				}).error(function (data) {
					console.log("Error getting" + $scope.BASE_URL + "/" + dbname + "/bus", data);
				});
				
				$http.get($scope.BASE_URL + "/" + dbname + "/var_ver")
				.success(function(varver_data) {
					angular.forEach(varver_data, function(varver){
						varver.file_name = dbname;
						varver.selected = false;
						varver.new_entry = false;
						varver.buses = $scope.ecu_buses.get(dbname);
						
						var newTemp = $filter("filter")($scope.evcCollection, {ecu_id:varver.ecu_id}, true);
						if(newTemp.length === 0) {
							console.log("new ecu found",newTemp);
							varver.new_entry = true;
							$scope.new_entry_params.push("xmit_"+varver.id);
							$scope.new_entry_params.push("gwbitpos_"+varver.id);
							$scope.new_entry_params.push("bus_"+varver.id);
						}
						if(varver.has_11_bit_ids && !varver.has_29_bit_ids) {
							varver.canbitsize = "11";
						} else if(!varver.has_11_bit_ids && varver.has_29_bit_ids) {
							varver.canbitsize = "29";
						} else {
							varver.canbitsize = null;
						}
						$scope.ecuVarVerCollection.push(varver);
					});
					console.log("Var Ver Data", $scope.ecuVarVerCollection);
				}).error(function(data){
					console.log("Error getting" + $scope.BASE_URL + "/" + dbname + "/var_ver",data);
				});
			});
			console.log("Var Ver Data", $scope.ecuVarVerCollection);
			for(var i=0; i < $scope.ecuVarVerCollection; i++) {
				var ecu_varver = $scope.ecuVarVerCollection[i];
				var newTemp = $filter("filter")($scope.evcCollection, {ecu_id:ecu_varver.ecu_id});
				if(newTemp === null) {
					console.log("new ecu found");
					ecu_varver.new_entry = true;
				} else {
					console.log("Existing found",ecu_varver.ecu_id);
					ecu_varver.new_entry = false;
				}
			}
	}).error(function(data) {
			$scope.status = data;
			console.log("Error in get /yb",data);
		});
		console.log("loadEcuPC completed");
	};
							   
	$scope.showEngVehConfigs = function(yb) {
		$scope.selectedYb = yb;
		var url = $scope.BASE_URL + "/global/" + yb.year + "/" + yb.body;
		$scope.searchText = yb.year + " " + yb.body;
		$http.get(url)
		.success(function(data) {
			$scope.evcCollection = data;
			$scope.loadEcuPC();
		}).error(function(data) {
			$scope.status = data;
			console.log("Error in get /global/"+yb.year+"/"+yb.body,data);
		})};
							   
	$scope.done = function() {
		$location.path("/download");
	};
	
	$scope.set_selected_checkbox = function(ecu_var_ver_clicked) {
		ecu_var_ver_clicked.selected = !ecu_var_ver_clicked.selected;
		angular.forEach($scope.var_ver_selected, function(ecu_var_ver) {
			if(ecu_var_ver_clicked !== ecu_var_ver) {
				ecu_var_ver.selected = false;
				var index = $scope.var_ver_selected.indexOf(ecu_var_ver);
				if (index > -1) {
    				$scope.var_ver_selected.splice(index, 1);
					return;
				}
			}
		});
		angular.forEach($scope.var_ver_selected_new_entry, function(ecu_var_ver) {
			if(ecu_var_ver_clicked !== ecu_var_ver) {
				ecu_var_ver.selected = false;
				var index = $scope.var_ver_selected_new_entry.indexOf(ecu_var_ver);
				if (index > -1) {
    				$scope.var_ver_selected_new_entry.splice(index, 1);
					return;
				}
			}
		});		
	};
}]);