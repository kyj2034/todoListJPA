const path  =require('path');

module.exports = {
  outputDir: path.resolve(__dirname, "../" + "main/resources/static"),
  devServer: {
    proxy: {
        '/todolist':{
            target: 'http://localhost:9001',
            ws: true,
            changeOrigin: true
        }
    }
  }
}
