app.controller('crtlNote', [ '$scope','$base64','$http','$uibModal', function($scope,$base64,$http,$uibModal) {
	$scope.noteForm={};
	$scope.noteForm.title="";
	$scope.noteForm.content="";
	$scope.simpleConfig = {
		serverUrl : '',
		toolbars : [ [ 
			'fullscreen', 'source', '|', 'undo', 'redo', '|',
			'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
			'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
			'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
			'directionalityltr', 'directionalityrtl', 'indent', '|',
			'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
			'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
			'emotion', 'map', 'insertframe', 'insertcode',  'pagebreak', 'template', 'background', '|',
			'horizontal', 'date', 'time', 'spechars', 'wordimage', '|',
			'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', 'charts', '|',
			'print', 'preview', 'searchreplace', 'help', 'drafts'
		 ] ]
	};
	$scope.saveNoteForm = function(){
		if(typeof $scope.noteForm.content == 'undefined'){
			$scope.noteForm.content='';
		}
		$http.post('noteService.json', {content:$scope.noteForm.content,title:$scope.noteForm.title}).success(function(data){
			alert(JSON.stringify(data))
		});
	};
	
	$scope.open = function (size) {

	    var modalInstance = $uibModal.open({
	      animation: $scope.animationsEnabled,
	      templateUrl: 'views/about/about.html',
	      controller: 'crtlabout',
	      size: size,
	      resolve: {
	        items: function () {
	          return $scope.items;
	        }
	      }
	    });
	};
} ]);