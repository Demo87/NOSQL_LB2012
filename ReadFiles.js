var walk　　= require('walk');
var filenames =　[]; 

// Walker options
var walker　= walk.walk('./infochimps/metar', { followLinks: false });

walker.on('file', function(root, stat, next) {
　　// Add this file to the list of files
　　filenames.push('metar/' + stat.name);
　　next();
});

walker.on('end', function() {
　　console.log(filenames);
}); 
