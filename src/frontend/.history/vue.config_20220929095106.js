const path  =require('path');

module.exports = {
  outputDir: path.resolve(__dirname, "../" + "main/resources/static"),
  devServer: {
    proxy: {
        '/todolist':{
            target: 'http://localhost:9000',
            ws: true,
            changeOrigin: true
        }
    }
  }
}
