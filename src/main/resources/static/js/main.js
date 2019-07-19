console.log("Test:","The main.js file is loaded!");

var TMOD = {

    config: {
        targetLanguagesUrl: "http://localhost:8080/targetlanguages/",
        translateUrl : "http://localhost:8080/translate/",
        sourceLangSelector: "#source_lang",
        targetLanguagesBlock: "#select-targetlanguage-block",
        formFieldSelector : ".form-field",
        id:"id",
        textAreaSelector : "#textarea",
        submitButton : "#submit-translation",
        translationFormId : "",
        maxFields: 3,
        translationEntryId: "#new-entry-row",
        loadIconSelector: "#submit-translation .load-icon",
        textButtonSelector: "#submit-translation span",
        hideClass : "d-none",
        empty: ""
    },

    showTargetLanguages: function (){
        $(this.config.sourceLangSelector).change(function() {
            if($(this).val() !== TMOD.config.empty){
                $.ajax({
                    type: "get",
                    url: TMOD.config.targetLanguagesUrl + $(this).val(),
                    dataType : 'html',
                    success : function(response){
                        $(TMOD.config.targetLanguagesBlock).html($.parseHTML(response));
                    },
                    error : function(){
                        $(TMOD.config.sourceLangSelector).val(TMOD.config.empty);
                    }
                });
            }
        });
    },

    sendPostRequest: function(fieldsSelector){

            var formData = {}
            $(fieldsSelector).each(function(){
                formData[$(this).data(TMOD.config.id)] = $(this).val();
            });
            $.ajax({
                type: "post",
                url: TMOD.config.translateUrl,
                contentType : "application/json",
                dataType : 'html',
                data : JSON.stringify(formData),
                success : function(response){
                    $(TMOD.config.textAreaSelector).val("");
                    $(TMOD.config.translationEntryId).after($.parseHTML(response));
                    alert("Translation submitted with success");
                    $(TMOD.config.loadIconSelector).addClass(TMOD.config.hideClass);
                    $(TMOD.config.textButtonSelector).removeClass(TMOD.config.hideClass);
                    $(TMOD.config.submitButton).prop("disabled", false);
                },
                error : function(){
                    alert("Something went wrong, please submit again");
                    $(TMOD.config.loadIconSelector).addClass(TMOD.config.hideClass);
                    $(TMOD.config.textButtonSelector).removeClass(TMOD.config.hideClass);
                    $(TMOD.config.submitButton).prop("disabled", false);
                }
            });
    },

    submitTranslation: function(){
        $(TMOD.config.submitButton).click( function() {

            var fieldCounter = 0;
            $(TMOD.config.formFieldSelector).each(function() {
                if($.trim($(this).val()) !== TMOD.config.empty){
                    fieldCounter++;
                }
            });

            if(TMOD.config.maxFields === fieldCounter){
                $(TMOD.config.loadIconSelector).removeClass(TMOD.config.hideClass);
                $(TMOD.config.textButtonSelector).addClass(TMOD.config.hideClass);
                $(TMOD.config.submitButton).prop("disabled", true);

                TMOD.sendPostRequest(TMOD.config.formFieldSelector);
            }else{
                alert("All fields are mandatory");
            }

        });
    },

    init: function(){
        $(document).ready( function() {
            TMOD.showTargetLanguages();
            TMOD.submitTranslation();
        });
    }

};

TMOD.init();