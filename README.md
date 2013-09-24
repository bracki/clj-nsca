# clj-nsca

Clojure wrapper for [jsendnsca](https://code.google.com/p/jsendnsca/).
The project uses [Midje](https://github.com/marick/Midje/).

## How to run the tests

`lein midje` will run all tests.

`lein midje namespace.*` will run only tests beginning with "namespace.".

`lein midje :autotest` will run all the tests indefinitely. It sets up a
watcher on the code files. If they change, only the relevant tests will be
run again.

## Dependencies

Unfortunately `jsendnsca` is not available via Maven.
I've uploaded it to Clojars by myself.

Hence:
 
    cd ..
    svn checkout http://jsendnsca.googlecode.com/svn/trunk/ jsendnsca-read-only
    cd jsendnsca-read-only
    cd jsendnsca
    mvn install
    scp pom.xml target/jsendnsca-$VERSION.jar clojars@clojars.org:

## Integration testing

Install vagrant, yada yada.

Then:

    vagrant up

In some window:
    
    vagrant ssh -c "sudo tail -f /var/log/syslog"

Elsewhere:

    lein run

Expect to see something like:

```
Sep 22 20:32:25 debian-6 nsca[1559]: Caught SIGHUP - restarting...
Sep 22 20:32:25 debian-6 nsca[1559]: Starting up daemon
Sep 22 20:32:25 debian-6 nsca[1559]: Listening for connections on port 5667
Sep 22 20:33:28 debian-6 nsca[1576]: Connection from 10.0.2.2 port 46579
Sep 22 20:33:28 debian-6 nsca[1576]: Handling the connection...
Sep 22 20:33:29 debian-6 nsca[1576]: SERVICE CHECK -> Host Name: 'horst', Service Description: 'API', Return Code: '1', Output: 'Oh noes!!!'
Sep 22 20:33:29 debian-6 nsca[1576]: Command file '/var/lib/nagios3/rw/nagios.cmd' does not exist, attempting to use alternate dump file '/var/run/nagios/nsca.dump' for output
Sep 22 20:33:29 debian-6 nsca[1576]: Could not open alternate dump file '/var/run/nagios/nsca.dump' for appending
Sep 22 20:33:29 debian-6 nsca[1576]: End of connection...
```
