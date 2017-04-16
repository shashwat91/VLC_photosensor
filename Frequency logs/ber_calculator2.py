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
logFileName = 'valvue_hold_20.csv'
pathToFile = cwd+'/hz_20/'+logFileName
logFile  = open(pathToFile, 'r+')

freq = 20
sampling_freq = freq * 1
sampling_interval = round(1.0/sampling_freq,3)
first_sample = 9.192

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

corr_bits = 0
dec_bits = len(decoded_bit)
print "bits decoded:: %d"%dec_bits
for i in range(0, len(decoded_bit)):
	#print "%2.3f"%sample[i]+" -> %1d"%decoded_bit[i]+"	!!	%d"%(i%2)
	if(decoded_bit[i] == ((i+1)%2)):
		corr_bits = corr_bits + 1

bit_err = (float(dec_bits - corr_bits)/float(dec_bits)) *100
print "Correct bits:: %d"%corr_bits
print "Bit error rate = %.2f"%bit_err

#Data aquired and sample points 
plt.title('Acuired data and sampling points')
plt.xlabel('Seconds')
plt.ylabel('Value')
plt.plot(time,bit,'b')
plt.plot(sample,xs,'rx')
plt.ylim([0,1.5])
plt.xlim([13,14])

plt.show()