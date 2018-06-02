OS worked on: Windows 10
Language Java 1.8
Maven version 3.5.3

I used Maven so you can see POM.xml (in the A1 folder) for the libraries.
I developed on IntelliJ.

Requirement: Require Maven to build the project.

XML FORMAT:
if dealerid is FAKE_DEALER_ID  -> will not authorize
if dealeraccesskey is FAKE_DEALER_ACCESSKEY  -> will not authorize
other ids/keys  -> will authorize
if partnumber is  -> 3456 Out of stock
if 4567  -> No longer manufactured
if 5678  -> invalid part
Other partnumbers  -> success (if authorized and XML is valid)

HOW TO RUN:
1. Clone A1 folder somewhere. For example, by zip (unzip it afterwards) or by git.

2. Enter A1 folder on command line, so the present working directory is A1.

3. Use command: mvn package

(The above command will build the project, and run the tests, I have a total of 36 tests.)

(After Maven built the project, the jar executable will be in A1/target)

4. Enter target folder with: cd target

5. Run the jar executable with: java -jar A1-1.0-SNAPSHOT.jar inputfilename outputfilename

As you can see, the jar name might be different if you used some naming options.

The jar executable takes in 2 commandline arguments, inputfilename and outputfilename.

You should make sure the inputfile exists already and you can put it in the same target folder, then executable will parse in input XML,
and provide appropriate response in XML named outputfilename.

For example, I run this: java -jar A1-1.0-SNAPSHOT.jar input.xml output.xml

Then, I get my outputxml in output.xml.

If you want to run tests again, go back to A1 folder, and use: mvn test



