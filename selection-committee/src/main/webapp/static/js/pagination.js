$(document).ready(function () {
    $(".myselect").click(function () {

        //console.log($(this).attr("value"))
        window.location.href = updateURLParameter(window.location.href, "page", $(this).attr("value"))

    });
    $(".sort-order").click(function () {

        //console.log($(this).attr("value"))
        window.location.href = updateURLParameter(window.location.href, "sort", $(this).attr("value"))

    });

    $('input[type="checkbox"]').on('change', function (e) {

        let filterParams = [];
        $('input[type="checkbox"]').each(function (i) {
            if (this.checked) {

                filterParams.push($(this).attr("value"));
            }
        });
        console.log(filterParams);
        window.location.href =  updateURLParameter(window.location.href,"filter", filterParams.join(','))
    });


});



function updateURLParameter(url, param, paramVal) {
    let newAdditionalURL = "";
    let tempArray = url.split("?");
    let baseURL = tempArray[0];
    let additionalURL = tempArray[1];
    let temp = "";
    if (additionalURL) {
        tempArray = additionalURL.split("&");
        for (let i = 0; i < tempArray.length; i++) {
            if (tempArray[i].split('=')[0] != param) {
                newAdditionalURL += temp + tempArray[i];
                temp = "&";
            }
        }
    }

    let rows_txt = temp + "" + param + "=" + paramVal;
    return baseURL + "?" + newAdditionalURL + rows_txt;
}

