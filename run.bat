echo path:%~dp0 
set base=%~dp0
set class=%base%src
set class_path=%class%

javac %class_path%\*.java 
java -classpath %class_path% Main 

@pause 
