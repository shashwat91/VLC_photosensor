import os
import numpy as np
import matplotlib.pyplot as plt

cwd = os.getcwd()
logFileName = 'hz_original_40.csv'
pathToFile = cwd+'/'+logFileName
logFile  = open(pathToFile, 'r+')

freq = 40
sampling_freq = freq * 1.1
sampling_interval = round(1.0/sampling_freq,4)

print "File open:: ", logFileName
print "Sampling frequency:: %d"%sampling_freq
print "Sampling interval:: %f"%sampling_interval

read_data = logFile.readlines()
#data = [[0 for x in range(2)] for y in range(len(read_data))]
time = []
bit = []
sample = []
for i in range(0,len(read_data)):
	lines = read_data[i].split(':')
	if(int(lines[4]) == 0 or int(lines[4]) == 1):
		time.append(float(lines[2]))
		bit.append(int(lines[4]))

i = 0
xs=[]
sample.append(time[0])
xs.append(1)
while(sample[i]<= time[-1]):
	i=i+1
	sample.append(round(time[0]+(sampling_interval*i),4))
	xs.append(1)

#print sample
 
#Average Voltage inverted and scaled to unity for step response
plt.title('Equivalent Average Unity Step Input Response')
plt.xlabel('Seconds')
plt.ylabel('Value')
plt.plot(time,bit,'b')
plt.plot(sample,xs,'rx')
plt.ylim([0,1.5])
plt.xlim([42,43])


# Maximize Plot Window cmd
#figManager = plt.get_current_fig_manager()  # Maximize Plot Window cmd
#figManager.window.showMaximized()           # Maximize Plot Window cmd

plt.show()