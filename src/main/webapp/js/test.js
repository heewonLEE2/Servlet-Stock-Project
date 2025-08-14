const mainBtn = document.getElementById("mainBtn");
const jsonBtn = document.getElementById("jsonBtn");
const stockInfoBtn = document.getElementById("stockInfoBtn");

mainBtn.addEventListener("click", function () {
  axios
    .get("http://localhost:8888/FirstServlet")
    .then(function (response) {
	  console.log(response);
      console.log(response.data);
    })
    .catch(function (error) {
      console.error("Error fetching data:", error);
    });
});

jsonBtn.addEventListener("click", function () {
  axios
    .get("http://localhost:8888/JsonServlet")
    .then(function (response) {
      console.log(response.data);
    })
    .catch(function (error) {
      console.error("Error fetching data:", error);
    });
});

stockInfoBtn.addEventListener("click", function () {
  axios
    .get("http://localhost:8888/StockInfoServlet")
    .then(function (response) {
      console.log(response.data);
    })
    .catch(function (error) {
      console.error("Error fetching data:", error);
    });
});