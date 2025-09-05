FOLDER="/d/github/cgwebprojectintegration/newrelic"
APP_JAR="/d/github/cgwebprojectintegration/webapp/build/libs/webapp-1.0.0.jar"


if [ -d "$FOLDER" ]; then
  echo "✅ Folder exists: $FOLDER"
else
  echo "❌ Folder does not exist: $FOLDER"
fi

# validate folder and files
if [ ! -d "$FOLDER" ]; then
  echo "❌ Folder not found: $FOLDER"
  exit 1
fi

if [ ! -f "$FOLDER/newrelic.jar" ]; then
  echo "❌ newrelic.jar not found in $FOLDER"
  exit 1
fi

if [ ! -f "$FOLDER/newrelic.yml" ]; then
  echo "❌ newrelic.yml not found in $FOLDER"
  exit 1
fi

# run the app with New Relic
echo "✅ Starting application with New Relic agent..."

java -javaagent:$FOLDER/newrelic.jar \
     -Dnewrelic.config.file=$FOLDER/newrelic.yml \
     -jar $APP_JAR