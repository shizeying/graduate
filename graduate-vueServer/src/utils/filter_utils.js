import Vue from 'vue'
Vue.filter("formatDate", formatDate);
Vue.prototype.formatDate = formatDate;
function formatDate(value) {
  if (!value) {
    return ''
  }

  var date = new Date(value);
  var year = date.getFullYear();
  var month = date.getMonth() + 1;
  var day = date.getDate();
  if (month < 10) {
    month = "0" + month;
  }
  if (day < 10) {
    day = "0" + day;
  }
  return `${year}-${month}-${day}`;
}
Vue.filter("formatDateTime", function formatDateTime(value) {
  var date = new Date(value);
  var year = date.getFullYear();
  var month = date.getMonth() + 1;
  var day = date.getDate();
  var hours = date.getHours();
  var minutes = date.getMinutes();
  if (month < 10) {
    month = "0" + month;
  }
  if (day < 10) {
    day = "0" + day;
  }
  return year + "-" + month + "-" + day + " " + hours + ":" + minutes;
});

