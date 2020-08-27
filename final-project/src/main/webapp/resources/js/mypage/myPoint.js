$(function(){
    activateMenu()
    generatePointHistory()
})

const activateMenu = ()=>{
    $("#mypagemenu li").click(function(){
        const req = $(this).data('href')
        console.log('/mypage/'+req)
        $(location).attr('href', req)
    })
}
const dateToYMD = function (date) {
    return `${date.getFullYear()}.${date.getMonth()}.${date.getDate()}`;
}
const generatePointHistory = ()=>{
    const url = '/mypage/myPoint.do'
    $.ajax({
        url:url,
        type:'POST',
        dataType:'JSON'
    }).done(result => {
        console.log(result.pointHistory)
        const history = result.pointHistory
        $("#current-point").text(result.currPoint)
        history.forEach(row=>{
            const item = `<tr id="point-history-list-${row.id}">
                              <td class="history-title">${row.title}</td>
                              <td class="history-reg-date">${dateToYMD(new Date(row.regDate))}</td>
                              <td class="history-method"></td>
                              <td class="history-change"></td>
                          </tr>`
            $(`#point-history-body`).append(item)
            if(row.value > 0){
                $(`#point-history-list-${row.id} .history-method`).text('증가')
                $(`#point-history-list-${row.id} .history-change`).text(row.value)
            }else if(row.value === 0){
                $(`#point-history-list-${row.id} .history-method`).text('변동없음')
                $(`#point-history-list-${row.id} .history-change`).text(row.value)
            }else{
                $(`#point-history-list-${row.id} .history-method`).text('감소')
                $(`#point-history-list-${row.id} .history-change`).text(row.value*-1)
            }

        })

    }).fail({

    }).always({

    })
}