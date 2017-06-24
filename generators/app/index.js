'use strict';
var Generator = require('yeoman-generator');
var chalk = require('chalk');
const mkdirp = require('mkdirp');
var yosay = require('yosay');

function isURL(str) {
  var pattern = new RegExp('^(https?:\\/\\/)?'+ // protocol
    '((([a-z\\d]([a-z\\d-]*[a-z\\d])*)\\.?)+[a-z]{2,}|'+ // domain name
    '((\\d{1,3}\\.){3}\\d{1,3}))'+ // OR ip (v4) address
    '(\\:\\d+)?(\\/[-a-z\\d%_.~+]*)*'+ // port and path
    '(\\?[;&a-z\\d%_.~+=-]*)?'+ // query string
    '(\\#[-a-z\\d_]*)?$','i'); // fragment locator
  return pattern.test(str);
}


module.exports = Generator.extend({
  prompting: function () {
    // Have Yeoman greet the user.
    this.log(yosay(
      'Welcome to ' + chalk.red('generator-android-kotlin-clean') + ' generator!'
    ));

    var prompts = [{
      name: 'name',
      message: 'What is the name of your app?',
      store: true,
      default: this.appname,
      validate: function (input) {
        if (/^([a-zA-Z0-9_]*)$/.test(input)) {
          return true;
        }
        return 'Your application name cannot contain special characters or a blank space, using the default name instead : ' + this.appname;
      }
    },{
      name: 'displayName',
      message: 'What name will be displayed in the app?',
      store: true,
      default: 'Clean Example',
      validate: input => input.length > 50 ? 'Name is so long' : true,
    }, 
    {
      name: 'appPackage',
      message: 'What package will you be publishing the app under?',
      validate: function (input) {
        if (/^([a-z_]{1}[a-z0-9_]*(\.[a-z_]{1}[a-z0-9_]*)*)$/.test(input)) {
          return true;
        }
        return 'The package name you have provided is not a valid Java package name.';
      },
      default: 'com.example.clean',
      store: true
    }];

    return this.prompt(prompts).then(function (props) {
      this.props = props;
    }.bind(this));
  },

  writing: function () {
    const architecture = 'clean-architecture';
    const packageDir = this.props.appPackage.replace(/\./g, '/');
    const oldPackageDir = 'com/nelosan/clean';

    /**
     * The files to copy
     * @type {[[string]]}
     */
    const copyFiles = [
      ['buildsystem'],
      ['gradlew'],
      ['gradlew.bat'],
      [`presentation/src/main/res/mipmap-hdpi`],
      [`presentation/src/main/res/mipmap-mdpi`],
      [`presentation/src/main/res/mipmap-xhdpi`],
      [`presentation/src/main/res/mipmap-xxhdpi`],
    ];

    copyFiles.forEach(([src, dest = src]) => {
      this.fs.copy(`${this.sourceRoot()}/${architecture}/${src}`, `${dest}`, this.props);
    });

    /**
     * The files to template
     * @type {[[string]]}
     */
    const templateFiles = [
      ['local.properties'],
      ['gradle.properties'],
      ['settings.gradle'],
      ['Android-CleanArchitecture.iml'],
      ['build.gradle'],
      ['data/build.gradle'],
      ['domain/build.gradle'],
      ['presentation/build.gradle'],
      ['data/src/main/AndroidManifest.xml'],
      [`data/src/main/kotlin/${oldPackageDir}`, `data/src/main/kotlin/${packageDir}`],
      [`data/src/test/kotlin/${oldPackageDir}`, `data/src/test/kotlin/${packageDir}`],
      [`domain/src/main/kotlin/${oldPackageDir}`, `domain/src/main/kotlin/${packageDir}`],
      [`domain/src/test/kotlin/${oldPackageDir}`, `domain/src/test/kotlin/${packageDir}`],
      [`presentation/src/main/kotlin/${oldPackageDir}`, `presentation/src/main/kotlin/${packageDir}`],
      [`presentation/src/androidTest/kotlin/${oldPackageDir}`, `presentation/src/androidTest/kotlin/${packageDir}`],
      [`presentation/src/main/AndroidManifest.xml`, `presentation/src/main/AndroidManifest.xml`],
      ['data/_.gitignore', 'data/.gitignore'],
      ['data/_.hgignore', 'data/.hgignore'],
      ['data/data.iml'],
      ['data/proguard-rules.pro'],
      [`data/src/main/res/values`],
      ['domain/domain.iml'],
      ['presentation/_.gitignore', 'presentation/.gitignore'],
      ['presentation/_.hgignore', 'presentation/.hgignore'],
      ['presentation/presentation.iml'],
      ['presentation/proguard-rules.pro'],
      [`presentation/src/main/res/layouts`],
      [`presentation/src/main/res/values`],
      [`presentation/src/main/res/values-w820dp`],
    ];


    templateFiles.forEach(([src, dest = src]) => {
      this.fs.copyTpl(`${this.sourceRoot()}/${architecture}/${src}`, `${dest}`, this.props);
    });

  }
});
