var express = require('express');
const http = require('http');
var app = express();
var stringReplace = require('string-replace-middleware');

var KC_URL = process.env.KC_URL || "";

app.use(stringReplace({'KC_URL': KC_URL}))
    .use('/bank', express.static('.'))
    .use("/", function(req, res) {
        res.redirect("/bank");
      });  

app.get('/', function(req, res) {
    res.render('index.html');
});

app.get('/index.js', function(req, res) {
    res.render('index.js');
});
  
app.listen(8000);