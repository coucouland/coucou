const path = require('path');
const webpack = require('webpack');
var MiniCssExtractPlugin = require("mini-css-extract-plugin");
const GoogleFontsPlugin = require("@beyonk/google-fonts-webpack-plugin")

module.exports = {
  entry: './src/assets/js/coucou.js',
  output: {
    filename: 'js/coucou.bundle.js',
    path: path.resolve(__dirname, 'src/assets')
  },
  module: {
    rules: [
      {
        test: /\.css$/,
        use: [
		  {
            loader: MiniCssExtractPlugin.loader,
            options: {
            	'outputPath': 'css/',
            	'useRelativePath': true,
            	publicPath: '/assets/coucou/css/'
			}
          },
          'css-loader'
        ]
      },
	  {
        test: /\.(png|svg|jpg|gif)$/,
        use: [
		  {
            loader: 'file-loader',
            options: {
            	'outputPath': 'images/',
            	'useRelativePath': true,
            	publicPath: '/assets/coucou/images/'
			}
          }
	  	]
      },
	  {
        test: /\.(woff|woff2|eot|ttf|otf)$/,
        use: [
		  {
            loader: 'file-loader',
            options: {
            	'outputPath': 'fonts/',
            	'useRelativePath': true,
				publicPath: '/assets/coucou/fonts/'
			}
          }
        ]
      },
	  {
        test: require.resolve('jquery'),
        use: [
        	{
			  loader: 'expose-loader',
			  options: 'jQuery'
		    },
		    {
				loader: 'expose-loader',
				options: '$'
			}
		]
      }
    ]
  },
  plugins: [
    new MiniCssExtractPlugin({
        filename: "css/coucou.bundle.css"
    }),
    new GoogleFontsPlugin({
		fonts: [
			{ family: "Roboto" }
		],
		path: 'fonts/',
		filename: 'css/fonts.css'
	})
  ]
};
