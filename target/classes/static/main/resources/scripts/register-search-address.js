const addressSearchButton = window.document.getElementById('address-button');
//const addressPostal = window.document.getElementById('address-postal');
const addressPrimary = window.document.getElementById('address-primary');
//const addressSecondary = window.document.getElementById('address-secondary');

let width = 500;
let height = 500;

addressSearchButton.addEventListener('click', () => {
    new daum.Postcode({
        width: width,
        height: height,
        oncomplete: function(data) {
            //addressPostal.value = data.zonecode;
            addressPrimary.value = data.roadAddress;
            addressSearchButton.querySelector('span').innerText = '다시 주소 검색';
            addressPrimary.classList.remove('hidden');
        }
    }).open({
        left: (window.screen.width / 2) - (width / 2),
        top: (window.screen.height / 2) - (height / 2)
    });
})
