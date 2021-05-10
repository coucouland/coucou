const apiUrl = "api/v1/village.infinity.json";
const urlParams = new URLSearchParams(window.location.search);

function displayVillage(village) {
    return `
        <div class="card blue-grey darken-1">
            <div class="card-content white-text">
                <span class="card-title">${village.title}</span>
                <p>I am a very simple card. I am good at containing small bits of information.
                    I am convenient because I require little markup to use effectively.</p>
            </div>
            <div class="card-action">
                <a href="#">This is a link</a>
                <a href="#">This is a link</a>
            </div>
        </div>
    `;
}

function displayResults() {
    $.getJSON(apiUrl + "?q=" + urlParams.get("q"), function(data) {
        if (data.villages.count == 0) {
            $("#villages").html("<h1>No match</h1>");
        } else {
            $("#villages").html("<h1>" + data.villages.count + " matching villages</h1>");

            var results = [];
            $.each(data.villages.list, function(k, v) {
                if (!k.startsWith('jcr:')) {
                    results.push(displayVillage(v));
                }
            })
            $("#villages").append($("<div/>", {
               "id": "search-results",
               html: results.join("")
            }));
        }
    });
}

$(document).ready(displayResults());
