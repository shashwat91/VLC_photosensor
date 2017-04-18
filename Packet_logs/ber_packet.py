import os
import numpy as np
import matplotlib.pyplot as plt
from bisect import bisect_left

def takeClosest(myList, myNumber):
    pos = bisect_left(myList, myNumber)
    if pos == 0:
        return myList[0]
    if pos == len(myList):
        return myList[-1]
    before = myList[pos - 1]
    return before

cwd = os.getcwd()
logFileName = 'hz_original_40.csv'
pathToFile = cwd+'/'+logFileName
logFile  = open(pathToFile, 'r+')

freq = 40
sampling_freq = freq * 1
sampling_interval = round(1.0/sampling_freq,3)
first_sample = 41.262

print "File open:: ", logFileName
print "Sampling frequency:: %d"%sampling_freq
print "Sampling interval:: %.3f"%sampling_interval

read_data = logFile.readlines()
#data = [[0 for x in range(2)] for y in range(len(read_data))]
time = []
bit = []
sample = []
for i in range(0,len(read_data)):
	lines = read_data[i].split(':')
	#if(int(lines[6]) == 0 or int(lines[6]) == 1):
	time.append(float(lines[2]))
	bit.append(int(lines[4]))

i = 0
xs=[]
sample.append(first_sample)
xs.append(1)
while(sample[i]< time[-1]):
	i=i+1
	sample.append(round(first_sample+(sampling_interval*i),4))
	xs.append(1)

if(sample[-1] > time[-1]):
	del sample[-1]
	del xs[-1]
 
decoded_bit = []
for i in range(0,len(sample)):
	try:
		#print "%.3f"%time[time.index(sample[i])]+"	->	%d"%bit[time.index(sample[i])]
		decoded_bit.append(bit[time.index(sample[i])])
	except ValueError:
		closest = takeClosest(time,sample[i])
		decoded_bit.append(bit[time.index(closest)])
		#print "value not found %.3f"%sample[i]+" but chosen %.3f"%closest+" 	->	%d"%bit[time.index(closest)]

print "decoded bits ::"
print decoded_bit

packet_progress = 0
count = 0
packet = [0 for x in range(8)]
for i in range(0,len(decoded_bit)):
	if ((packet_progress == 0) and (decoded_bit[i] == 1)):
		if ((decoded_bit[i+1] == 0) and (decoded_bit[i+2] == 1) and (decoded_bit[i+3] == 0)):
			packet_progress = 1
			i = i+4

	elif ((packet_progress == 1) and (count<8)):
		packet[count] = decoded_bit[i]
		count = count+1

	elif ((packet_progress == 1) and (count==8)):
		parity = np.sum(packet)%2
		if parity == decoded_bit[i]:
			print(packet)
		else:
			i = i-11
		packet_progress = 0
		count = 0

#Data aquired and sample points 
plt.title('Acuired data and sampling points, '+logFileName)
plt.xlabel('Time (sec)')
plt.ylabel('Value')
plt.plot(time,bit,'b')
plt.plot(sample,xs,'rx')
plt.ylim([0,1.5])
plt.legend(['Acquired data', 'Sample points'])
plt.xlim([41,42])

#mng = plt.get_current_fig_manager()
#mng.full_screen_toggle()
#plt.show()