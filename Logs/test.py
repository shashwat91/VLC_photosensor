import os

cwd = os.getcwd()
print(cwd)
folder = 'Test1'
logFileName = 'log_original.txt'
pathToFolder = cwd+'/'+folder
pathToFile = pathToFolder+'/'+logFileName
logFile  = open(pathToFile, 'r+')
logFile2 = open(pathToFolder + '/log_modified1.csv','w')

read_data = logFile.readlines()
#print(read_data[2:])
for i in range(2,len(read_data)):
	lines = read_data[i].split(':')
	#print (lines)
	logFile2.writelines(["%s" % item  for item in lines])

