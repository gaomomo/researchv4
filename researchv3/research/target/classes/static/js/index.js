$(function(){
    $('li').hover(function(){
        $(this).css("background-color","#F3F6FA");
    },
    function(){
        $(this).css("background-color","white");
    });
    $(".submitbutton").hover(function(){
        $(this).css("background-color","#486FCA");
    },
    function(){
        $(this).css("background-color","#5A83E5");
    });
    $("#submit_button").click(function(){
        var ins = document.getElementsByClassName("rad");
        var cs = [];
        for(i in ins){
            if(ins[i].checked){
                cs.push(ins[i].id);
            }
        }
        $("#pickedid").attr("value",cs);
    });

    // $(".submitbutton").click(function(){
    //     var obj={
    //         "childName":$("#childName").val(),
    //         "childAge":$("#age").val(),
    //         "parName":$("#parName").val(),
    //         "phone":$("#phone").val(),
    //         "school":$("#school").val(),
    //         "date":$("#date").val(),
    //         "picked":cs
    //     };
    //
    //     $.ajax({
    //         async:false,
    //         type : "POST",
    //         url : "/getResult",
    //         dataType : "json",
    //         data :  JSON.stringify(obj),
    //         contentType: "application/json;charset=utf-8",
    //        success : function(data) {
    //             alert(data.result );
    //
    //            // if(data.result == "updatepwdsuccess"){
    //            //     $('.xg_con .shade').css('display','block');
    //            //     $('.xg_con .success label').text('修改密码成功');
    //            //     $('.xg_con .success').css('display','block');
    //            //     showShade();
    //            //     $('.xg_con .success .cal_pic').click(function(){
    //            //         var str = data.url
    //            //         if(str == null){
    //            //             window.location.href = "../";
    //            //         }else{
    //            //             window.location.href = "http://localhost:8080/FinancialAnalysisSystem"+str;
    //            //         }
    //            //     });
    //
    //            // }
    //           },
    //    });
    // });

   

});