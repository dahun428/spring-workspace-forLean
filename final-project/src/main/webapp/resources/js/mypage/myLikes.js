let isLast = false
$(function(){
    activateMenu()
    $("#myModal .card-body").unbind('mouseenter mouseleave')
    $(window).scroll(() => {
        const scrollTop = $(window).scrollTop()
        const windowHeight = $(window).height()
        const docHeight = $(document).height()

        if(scrollTop > docHeight - windowHeight - 30){
            generateList()
        }
    })
    generateList()

    $(document).on('click','#modalBuyBtn', (event)=>{
        const performanceNo = $(event.target).data('no')
        const url = '/payment/step1.do?no='+performanceNo
        $(location).attr('href',url)
    })

    $(document).on('click','.like-list',function(){
        const performanceId = $(this).data('performanceId')
        $.ajax({
            type:"GET",
            url:"/performance/detail.do",
            data: {id:performanceId},
            dataType: 'json',
            success: function(performance) {


                $("#modalImg").attr("src", `/resources/sample-images/${performance.imagePath}`);
                var rating = performance.rating;
                if (performance.rating == "0") {
                    rating = "전체";
                }
                console.log(performance)
                $("#modalAge").text(rating);
                $("#modalTitle").text(performance.title);
                console.log(performance.genres)
                var performanceGenres = "";
                for (var idx=0; idx < performance.genres.length; idx++) {
                    performanceGenres += performance.genres[idx]+ " ";
                }

                $("#modalGenre").text(performanceGenres);

                $("#modalRunningTime").text(performance.runningTime);

                var period = performance.startDate + " ~ " +  performance.endDate;
                $("#modalPeriod").text(period);
                $("#modalCategory").text(performance.category);
                $("#modalHallName").text(performance.hallName);

                let seatPrices = "";
                for (let idx =0; idx < performance.seatPrices.length; idx++) {
                    seatPrices += performance.seatPrices[idx].seatRate + "석 "
                        + numberWithComma(performance.seatPrices[idx].price) + "원 ";
                }


                $("#modalSeatInfo").text(seatPrices);

                var tags="";
                for (var idx=0; idx < performance.genres.length; idx++) {
                    tags += "#"+performance.genres[idx];
                }

                $("#modalTags").text(tags).css("color", "#06F");

                $("#modalLikes").text(performance.likes);
                $("#modalAfterParty").text();

                $("#modalDetailtitle").text(performance.title);
                $("#modalDetailHallName p:eq(0)").text(performance.hallName);
                $("#modalDetailHallName p:eq(1)").text("("+performance.hallAddress+")");
                $("#modalDetailPeriod").text(period);
                //$("#modalDetailTime").text(performance.expla);
                $("#modalDetailRunningTime").text(performance.runningTime);
                $("#modalDetailSeatInfo").text(seatPrices);

                if (rating != '전체') {
                    rating = "만 "+rating+"이상 관람가";
                }else {
                    rating = rating+"관람가";
                }

                $("#modalDetailAge").text(rating);
                $("#modalDetailExplain").text(performance.explain);


                $("#modalBuyBtn").attr("data-no", performance.id);
                $("#showMateGroupBtn").attr("data-no", performance.id);


            }
        })

        // 성별 그래프
        var genderColors=['skyblue', '#E23B3B'];

        var donutOptions= {
            cutoutPercentage: 30, //도넛두께 : 값이 클수록 얇아짐
            legend: {
                position:'bottom',
                padding:5,
                labels: {
                    pointStyle:'circle',
                    usePointStyle:true
                }
            }
        };

        var chDonutData = {
            labels: ['남자', '여자'],
            datasets: [ {
                backgroundColor: genderColors.slice(0,2),
                borderWidth: 0,
                data: [74, 40] // 데이터
            } ]
        };

        var $chDonut = $("#chartGender");

        if ($chDonut) {
            new Chart($chDonut, {
                    type: 'pie',
                    data: chDonutData,
                    options: donutOptions
                }
            );
        }

        // 나이대별 관람추이
        var $chBar = $("#chartAge");

        if ($chBar) {
            new Chart($chBar, {
                type: 'bar',
                data: {
                    labels: ['10대', '20대', '30대', '40대', '50대↑'],
                    datasets: [{
                        label: '# of Numbers',
                        data: [12,34,47,16,12],	// 퍼센트 구해서 넣기
                        backgroundColor: [
                            'rgba(54, 162, 235, 0.2)',
                            'rgba(54, 162, 235, 0.2)',
                            'rgba(54, 162, 235, 0.2)',
                            'rgba(54, 162, 235, 0.2)',
                            'rgba(54, 162, 235, 0.2)'
                        ],
                        borderColor: [
                            'rgba(54, 162, 235, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(54, 162, 235, 1)'
                        ],
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        yAxes: [{
                            ticks: {
                                beginAtZero: true
                            }
                        }]
                    }
                }

            })
        }

        $("#myModal").modal("show");
    })

    $("#mypagemenu li").click((event) => {
        const url = $(event.target).data('href')
        $(location).attr('href', url)
    })
})


var dateToYMD = function(date){
    return date.getFullYear()+'.'+date.getMonth()+'.'+date.getDate();
}

let currNo = 0;
let generateList = function(){
    if(isLast){
        return
    }
    let user = {
        id:$('#login-user-id').text(),
        point : currNo
    }
    var template = $("#like-list-template").clone()
    $("#like-list-template").remove()
    

    $.ajax({
        url:'/mypage/myLikes.do',
        type:'POST',
        contentType : 'application/json; charset=utf-8',
        data:JSON.stringify(user),
        datatype: 'json'
    }).done(result => {
        var likes = result.likes
        
        if(likes.length){
            if(likes.length < 5){
                isLast = true
            }
            currNo += likes.length
            
            likes.forEach((like)=>{

                let newId = 'like-list-'+like.id
                template.attr('id', newId)
                template.addClass('like-list')
                template.attr('data-performance-id',like.id)
                newId = '#'+newId
                $(".container").append(template.clone())
                $(`${newId} .like-img`).attr('src',`/resources/sample-images/${like.imagePath}`)
                $(`${newId} .like-title`).text(like.title)
                $(`${newId} .like-category`).text(like.category)
                $(`${newId} .like-genre`).empty()
                like.genre.forEach((name) =>{
                    $(newId+" .like-genre").append(`<span>${name}</span>`)
                })
                $(`${newId} .like-running-time`).text(like.runningTime)
                $(`${newId} .like-provider    `).text(like.provider.length > 8 ? like.provider.substring(0,8)+'...' : like.provider)
                $(`${newId} .like-start-date  `).text(dateToYMD(new Date(like.startDate)))
                $(`${newId} .like-end-date    `).text(dateToYMD(new Date(like.endDate)))
                $(`${newId} .like-hall-name   `).text(like.hallInfo.name)
                $(`${newId} .like-explain     `).text(like.explain.length > 50 ? like.explain.substring(0,49)+'...' : like.explain)
            })
        }
    }).fail(function(){

    }).always(function(){

    })
}
let numberWithComma = function(num) {
    let regexp = /\B(?=(\d{3})+(?!\d))/g;
    return num.toString().replace(regexp, ',');
}

let activateMenu = function(){
    $("#mypagemenu li").click((event) => {
        const url = $(event.target).data('href')
        $(location).attr('href', url)
    })
}