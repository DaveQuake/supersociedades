$(".bar-search").find(".search-bar-keywords-input").attr("placeholder", "Buscar");
$(".bar-search").find(".input-group-item.search-bar-keywords-input-wrapper").attr("padding", "0 !important");

/*
if($(".bar-search").find(".search-bar-keywords-input").length > 1){
  $(".bar-search").find(".search-bar-keywords-input").attr("placeholder", "Buscar");
}*/

//Contraste
var contras = false;

function change_contrast(){

  console.log("Mensaje desde chabge contrast");
	if(contras == false){
		$("html").css("filter", "invert(100%)");
		contras = true;
	}
	else{

		$("html").css("filter", "invert(0%)");
		contras = false;
	} 
}



//incremento o reduccion de fuente
function big_small(a){

  switch (a) {

      case "aum":

              let aaa = document.getElementsByTagName("*")
              for (var i = 0; i < aaa.length; i++) {
                  curSize= parseInt($( aaa[i] ).css("font-size")) + 1;
                  if(curSize<=20){
                      $(aaa[i]).css('font-size', curSize);
                  }
              }
          break;

      case "dism":

              let bbb = document.getElementsByTagName("*")
              for (var i = 0; i < bbb.length; i++) {
                  curSize= parseInt($( bbb[i] ).css("font-size")) - 1;
                  if(curSize>=14){
                      $(bbb[i]).css('font-size', curSize);
                  }
              }
          break;
      default:
          break;
  }
}

function increase(){         
    big_small("aum");
}

function reduce(){ 
    big_small("dism");
}


var classesFonts = [
  "fsize-super0",
  "fsize-super1",
  "fsize-super2",
  "fsize-super3",
  "fsize-super4",
  "fsize-super5",
  "fsize-super6",
  "fsize-super7",
];
var classIndex = 2;



function changeClass(previous, next) {
  if (previous != next) {
    var htmlElement = document.querySelector("html");
    htmlElement.classList.remove(classesFonts[previous]);
    htmlElement.classList.add(classesFonts[next]);
  }
}


/*Menú soporte chat */
var caseItem = document.getElementById("case");
var participationItem = document.getElementById("participation");
var complaintsItem = document.getElementById("complaints");
var pqrsdItem = document.getElementById("pqrsd");
var whatsappItem = document.getElementById("whatsapp");
var closeItem = document.getElementById("close");
document.getElementById("ayuda").addEventListener("click", function () {
  document.getElementById("support").classList.toggle("menu_bg-open");
  caseItem.classList.toggle("menu_item-active");
  participationItem.classList.toggle("menu_item-active");
  complaintsItem.classList.toggle("menu_item-active");
  pqrsdItem.classList.toggle("menu_item-active");
  whatsappItem.classList.toggle("menu_item-active");
  closeItem.classList.toggle("menu_item-active");
});

document.getElementById("close").addEventListener("click", function () {
  document.getElementById("support").classList.toggle("menu_bg-open");
  caseItem.classList.toggle("menu_item-active");
  participationItem.classList.toggle("menu_item-active");
  complaintsItem.classList.toggle("menu_item-active");
  pqrsdItem.classList.toggle("menu_item-active");
  whatsappItem.classList.toggle("menu_item-active");
  closeItem.classList.toggle("menu_item-active");
});


function redirect_files(url_dtn){

	let pre_vsd = "https://www.draw.io?lightbox=1&edit=_blank#U";
	//url local
  //let pag_view_file_our = "/web/supersociedades/ver_archivos";
  let pag_view_file_our = "/web/guest/ver_archivos";


	//e.preventDefault();
	//let url_dtn = $(this).attr("href");
	let file_entid = "";
	console.log("La url es: " + url_dtn);


	//validar si se trata de un resultado de búsqueda     ----vsd ok
	/////////////////////////////////////////////////////////////////
	if(url_dtn.includes("liferay_portal_search_web_search_results")){

		if(url_dtn.includes("assetEntryId=")){

				let sub_fil_entry_id = url_dtn.split("assetEntryId=")[1];
				let ind_end_id = sub_fil_entry_id.indexOf("&_com");
				let fil_entry_id = sub_fil_entry_id.substring(0,ind_end_id);

				console.log("El file entry id es: " + fil_entry_id);


				Liferay.Service(
					  '/assetentry/get-entry',
					  {
					    entryId: parseInt(fil_entry_id)
					  },
					  function(obj) {
					    
						    Liferay.Service(
								  '/dlfileentry/get-file-entry',
								  {
								    fileEntryId: parseInt(obj.classPK)
								  },
								  function(obj) {

								  	if( (obj.extension == "vsd") || obj.extension == "vsdx"){
						          		//window.location.href = pre_vsd + window.location.origin +  "/documents/" + obj.groupId + "/" + obj.folderId + "/" + obj.title + "." + obj.extension
						          		let url_doc_vsd = pre_vsd + window.location.origin +  "/documents/" + obj.groupId + "/" + obj.folderId + "/" + obj.title;
						          		
						          		if( url_doc_vsd.includes("." + obj.extension)){
						          			window.location.href = url_doc_vsd;
						          		}else{
						          			window.location.href = url_doc_vsd + "." + obj.extension;
						          		}


						          	}else{
								  	
								  	window.location.href= url_dtn;
								    //window.location.href= "/documents/" + obj.groupId + "/" + obj.folderId + "/" + obj.title + "?previewFileIndex=1";
									}						  
								  }
							);
					  }
				);
		}else{

			window.location.href= url_dtn;
		}



	//validar si se trata de un wigdet de documents and media ----vsd ok
	////////////////////////////////////////////////////////////
	}else if(url_dtn.includes("/view_file/")){

		let spliturl = url_dtn.split("/view_file/")[1];
		let file_entid = spliturl.split("?_com_liferay_document_library_web")[0];

		//window.location.href= url_dtn;	

	    Liferay.Service(
		      '/dlapp/get-file-entry',
		      {
		          fileEntryId: file_entid
		      },
		      function(obj) {
		          console.log(obj);
		          
		          if( (obj.extension == "vsd") || (obj.extension == "vsdx") ){
		          	//window.location.href = pre_vsd + window.location.origin +  "/documents/" + obj.groupId + "/" + obj.folderId + "/" + obj.title + obj.extension;
		          	let url_doc_vsd = pre_vsd + window.location.origin +  "/documents/" + obj.groupId + "/" + obj.folderId + "/" + obj.title;

		          	if( url_doc_vsd.includes("." + obj.extension)){
	          			window.location.href = url_doc_vsd;
	          		}else{
	          			window.location.href = url_doc_vsd + "." + obj.extension;
	          		}
		          }else{
		          	window.location.href = url_dtn;
		          //console.log("La url es: " + "/documents/" + obj.groupId + "/" + obj.folderId + "/" + obj.title + "?previewFileIndex=1");
				      }		      
		      }
        );
	


	//validar si se trata de un link a un documento desde un contenido web básico  ---vsd ok
	///////////////////////////////////////////////////////////////////////////////////////////
	}else if(url_dtn.includes("/documents/") ){

		/*let parturl = url_dtn.split("/documents/")[1];

		if( parturl.includes(".ppt")  ){
			let parturl2 = parturl.split(".ppt")[0];

			let indice = parturl2.lastIndexOf("/");
			let ind = indice + 1;

			console.log(parturl2);
		}*/
		console.log("ulrBase sin recorte: "+url_dtn);
		let last_split = url_dtn.lastIndexOf("/");
		let url_file = url_dtn.substring(0,last_split);

		console.log("ulrBase cor recorte: "+url_file);


		if( (url_file.includes(".vsd")) || (url_file.includes(".vsdx")) ){
			window.open(pre_vsd + url_file, '_blank');
			//window.location.href = pre_vsd + url_file;
		}else{

			//console.log("La url del archivo es: " + url_file + "?previewFileIndex=1");
			//////window.location.href = url_file + "?previewFileIndex=1";

			if( url_file.includes(".pdf") || url_file.includes(".PDF") ){
                  
        //console.log("Diego ulrBase sin cortes, es PDF: "+url_dtn);
        window.open(url_dtn, '_blank');

      }
      if( url_file.includes(".doc") || url_file.includes(".DOC") || url_file.includes(".docx") || url_file.includes(".DOCX") || url_file.includes(".xls") || url_file.includes(".XLS") || url_file.includes(".xlsx") || url_file.includes(".XLSX")){
                  
        //console.log("Diego ulrBase sin cortes, es PDF: "+url_dtn);
        window.open("https://view.officeapps.live.com/op/view.aspx?src="+url_file, '_blank');
				
      }else{
        //console.log("Diego ulrBase no es PDF: "+url_dtn);
        window.open(url_dtn, '_blank');
        //window.location.href = pag_view_file_our + "?url=" + url_file + "?previewFileIndex=1";
			}

		}
	

	//Validar si se trata de un acceso desde un publicador de contenidos -----vsd ok
	//////////////////////////////////////////////////////////////////////////////////
	}else if( url_dtn.includes("/document/id/") ){

		let entrId = url_dtn.split("/document/id/")[1].split("?")[0];

		Liferay.Service(
			  '/assetentry/get-entry',
			  {
			    entryId: parseInt(entrId)
			  },
			  function(obj) {
			    //console.log(obj);

				    Liferay.Service(
						  '/dlfileentry/get-file-entry',
						  {
						    fileEntryId: parseInt(obj.classPK)
						  },
						  function(obj) {

						  	if( (obj.extension == "vsd") || (obj.extension == "vsdx") ){
				          		//window.location.href = pre_vsd + window.location.origin +  "/documents/" + obj.groupId + "/" + obj.folderId + "/" + obj.title + obj.extension
				          		let url_doc_vsd = window.location.href = pre_vsd + window.location.origin +  "/documents/" + obj.groupId + "/" + obj.folderId + "/" + obj.title;

				          		if( url_doc_vsd.includes("." + obj.extension)){
				          			//window.open(url_doc_vsd, '_blank');
				          			window.location.href = url_doc_vsd;
				          		}else{
				          			//window.open(url_doc_vsd + "." + obj.extension, '_blank');
				          			window.location.href = url_doc_vsd + "." + obj.extension;
				          		}

				        }else if( (obj.extension == "pdf") || (obj.extension == "png") || (obj.extension == "jpg") || (obj.extension == "jpeg") || (obj.extension == "gif") || (obj.extension == "bmp") || (obj.extension == "tif") || (obj.extension == "tiff") || (obj.extension == "dib") || (obj.extension == "jpe") || (obj.extension == "jfif") || (obj.extension == "heic") || (obj.extension == "hif") ){
                  //window.open(url_dtn, '_blank');
                  window.location.href = url_dtn;
                }
                else{
						  	//console.log("la url es:");
						    //console.log("/documents/" + obj.groupId + "/" + obj.folderId + "/" + obj.title + "?previewFileIndex=1");
						    ///window.location.href= "/documents/" + obj.groupId + "/" + obj.folderId + "/" + obj.title + "?previewFileIndex=1";
						    //window.open(pag_view_file_our + "?url=" + "/documents/" + obj.groupId + "/" + obj.folderId + "/" + obj.title + "?previewFileIndex=1", '_blank');
						    window.location.href = pag_view_file_our + "?url=" + "/documents/" + obj.groupId + "/" + obj.folderId + "/" + obj.title + "?previewFileIndex=1"
							  }						  
						  }
					);
			  }
		);

	}else{
		//window.open(url_dtn, '_blank');
		window.location.href = url_dtn;
	}

}

$(function() {

  //opciones de accesibilidad

  $("#button_text_down").click(function(evt){
    evt.preventDefault();
    reduce();
  });

  $("#button_text_up").click(function(evt){
      evt.preventDefault();
      increase();
  });


  $("#btnContrast").click(function(evt){
    evt.preventDefault();
    console.log("mensaje desde evento de click de contraste");
    change_contrast();
  });



  $(".main-goup").click(function (ev) {
    ev.preventDefault();
    $("body, html").animate(
      {
        scrollTop: "0px",
      },
      500
    );
  });

  $(".resp-maingoup").click(function (ev) {
    ev.preventDefault();
    $("body, html").animate(
      {
        scrollTop: "0px",
      },
      500
    );
  });


  //view_files();
  $("a").click(function(e){

    e.preventDefault();
    let url_dtn = $(this).attr("href");

    if(url_dtn == "javascript:;"){
      $(this).unbind('click');
      $(this).trigger("click");
    }else if($(this).attr("onclick") != undefined){
      $(this).unbind('click');
      $(this).trigger("click");
    }else{
    	redirect_files(url_dtn);
      console.log("Agregamosel evento");
    }
  })

  /*Botones accesibilidad tamaño responsive. */

  $(".show_acc").click(function(){
    $(".container-accesibility").css("display","block");
    $(".hide_acc").css("display","flex");
    $(".show_acc").css("display","none");
  });

  
  $(".hide_acc").click(function(){
    $(".container-accesibility").css("display","none");
    $(".hide_acc").css("display","none");
    $(".show_acc").css("display","flex");
  });


  $("#downLine").click(function () {
    //$(".container-accesibility").slideDown(400);
    $(".down-line").removeClass("show");
    $(".down-line").addClass("hide");
    $(".up-line").addClass("up-line-show");
    //$(".resp-accesibility").addClass("resp-active");
    $(".container-accesibility").css("display","block");
  });

  $("#upLine").click(function () {
    //$(".container-accesibility").slideUp(400);
    $(".down-line").addClass("show");
    $(".down-line").removeClass("hide");
    $(".up-line").removeClass("up-line-show");
    //$(".resp-accesibility").removeClass("resp-active");
    $(".container-accesibility").css("display","none");
  });

});


