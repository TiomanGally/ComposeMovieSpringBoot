mvn clean install
docker build -t milchkarton/compose:test
docker run --rm -e "omdb_api-key=${replace-with-your-omdb-key}" -p 8080:8080 -t milchkarton/compose:test
