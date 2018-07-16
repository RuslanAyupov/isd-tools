### ISD Tool WebApp
Web Application with usefull tools set, such as :

###### ISD WebCalc
> Solution cost estimation tool (WIP)

###### Alarms Management:
> This application developed for manage Tivoli alarms triggers and syncronize they with Confluence wiki.
> 
> https://confluence.intranet/

#### Changelog:

- v0.0.1 (initial version)
    - developed mvc (spring framework);
    - implemented security basic roles management (spring security);
- v0.0.2 (wip)
    - implement auditing service;
    - package with spring boot;
- v0.0.3 (wip)
    - added universal entity generation;
    - added alarms management entity and UI;
    - trying refactoring UI with clarity;      

#### Pre-requirements

+ PostgreSQL 9.x
+ NodeJS 8.x (LTS)
+ NPM / Angular CLI / WebPack / YO
+ Maven 3.x

#### Database preparation

+ Create DB:

```sql
-- Users
CREATE ROLE alarmsdba WITH LOGIN PASSWORD 'passw0rd';
ALTER ROLE alarmsdba CREATEDB;

-- Database
CREATE DATABASE alarms;
GRANT ALL PRIVILEGES ON DATABASE alarms TO alarmsdba;
```

+ Create Tables and Set default scheme

```sql
-- Grant Permissions
ALTER DATABASE alarms SET search_path TO public;
ALTER ROLE alarmsdba SET search_path TO public;

-- Table prepare
CREATE TABLE ALARM
(
    id serial primary key,
    SITNAME VARCHAR(1024) not null,
	APPL VARCHAR(512),
	MESSTEXT VARCHAR(1024),
	"DESC" VARCHAR(512),
	HPSM VARCHAR(512),
	BFUNC VARCHAR(512),
	EMAIL VARCHAR(512),
	CI VARCHAR(512),
	INFRASTRUCTURE VARCHAR(512),
	SMS VARCHAR(512),
	FMAIL VARCHAR(512),
	FSMS VARCHAR(512),
	SITTYPE VARCHAR(512) not null,
	FTLG VARCHAR(512),
	TLG VARCHAR(512),
	CHGJ VARCHAR(512),
    URL VARCHAR(1024),
    HPSM_OVVERRIDE(1024)
);
```

+ Filter queries:

```sql
-- Show total records
select count(*) from "PUBLIC".alarm;

-- Select deployed pages
select chgj, appl, bfunc, email, sms, tlg, url from "PUBLIC".alarm where url is not null;

-- Select pages to deploy
select chgj, appl, bfunc, email, sms, tlg, url from "PUBLIC".alarm where url is null;

-- Update
UPDATE "public".alarm
SET CHGJ='16.03.2017 Evgrashin. SWIFT Message Partner Closed', APPL='SWIFT', BFUNC='SWIFT communication', EMAIL='ASO_SWIFT@unicredit.ru', SMS='+79636554090', TLG='ASO_Corporate ASO_SWIFT', URL='';

-- Update custom Alarm URL row to NULL values
UPDATE "public".tezstsit
SET url = 'https://confluence-stage.intranet/pages/viewpage.action?pageId=99975264'
where id = 1;

-- Show version
select version();
```

#### Development
> In developemnt project used this DBMS:

```properties
url: jdbc:h2:file:./target/h2db/db/isdtools;DB_CLOSE_DELAY=-1
username: isdtools
password:
```


Before you can build this project, you must install and configure the following dependencies on your machine:

1. [Node.js][]: We use Node to run a development web server and build the project.
   Depending on your system, you can install Node either from source or as a pre-packaged bundle.

After installing Node, you should be able to run the following command to install development tools.
You will only need to run this command when dependencies change in [package.json](package.json).

    npm install

We use npm scripts and [Webpack][] as our build system.

Run the following commands in two separate terminals to create a blissful development experience where your browser
auto-refreshes when files change on your hard drive.

    ./mvnw
    npm start

[Npm][] is also used to manage CSS and JavaScript dependencies used in this application. You can upgrade dependencies by
specifying a newer version in [package.json](package.json). You can also run `npm update` and `npm install` to manage dependencies.
Add the `help` flag on any command to see how you can use it. For example, `npm help update`.

The `npm run` command will list all of the scripts available to run for this project.

##### Service workers

Service workers are commented by default, to enable them please uncomment the following code.

* The service worker registering script in index.html

```html
<script>
    if ('serviceWorker' in navigator) {
        navigator.serviceWorker
        .register('./service-worker.js')
        .then(function() { console.log('Service Worker Registered'); });
    }
</script>
```

Note: workbox creates the respective service worker and dynamically generate the `service-worker.js`

##### Managing dependencies

For example, to add [Leaflet][] library as a runtime dependency of your application, you would run following command:

    npm install --save --save-exact leaflet

To benefit from TypeScript type definitions from [DefinitelyTyped][] repository in development, you would run following command:

    npm install --save-dev --save-exact @types/leaflet

Then you would import the JS and CSS files specified in library's installation instructions so that [Webpack][] knows about them:
Note: there are still few other things remaining to do for Leaflet that we won't detail here.

For further instructions on how to develop with JHipster, have a look at [Using JHipster in development][].


##### Doing API-First development using openapi-generator

[OpenAPI-Generator]() is configured for this application. You can generate API code from the `src/main/resources/swagger/api.yml` definition file by running:
```bash
./mvnw generate-sources
```
Then implements the generated delegate classes with `@Service` classes.

To edit the `api.yml` definition file, you can use a tool such as [Swagger-Editor](). Start a local instance of the swagger-editor using docker by running: `docker-compose -f src/main/docker/swagger-editor.yml up -d`. The editor will then be reachable at [http://localhost:7742](http://localhost:7742).

Refer to [Doing API-First development][] for more details.

#### Building for production

To optimize the AlarmsWebApp application for production, run:

    ./mvnw -Pprod clean package

This will concatenate and minify the client CSS and JavaScript files. It will also modify `index.html` so it references these new files.
To ensure everything worked, run:

    java -jar target/*.war

Then navigate to [http://localhost:8080](http://localhost:8080) in your browser.

Refer to [Using ISD Tools in production][] for more details.

#### Testing

To launch your application's tests, run:

    ./mvnw clean test

##### Client tests

Unit tests are run by [Jest][] and written with [Jasmine][]. They're located in [src/test/javascript/](src/test/javascript/) and can be run with:

    npm test

#### Continuous Integration (optional)

To configure CI for your project, run the ci-cd sub-generator (`jhipster ci-cd`), this will let you generate configuration files for a number of Continuous Integration systems. Consult the [Setting up Continuous Integration][] page for more information.
