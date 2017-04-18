#!/bin/bash
MainDir=$(pwd)

PyName=$(find *.py ! -name 'test0.py' ! -name  'test1.py')
CheckPyName=$(echo $PyName | cut -f 1 -d '.')
Student=$(pwd | rev | cut -f 1 -d '/' | rev)

## check if correct filename
if [[ $CheckPyName == "GoogleMapReduce" ]]; then 
	test0=$(python3 ./test0.pyc)
	
	if [ $test0 == 1 ]; then
		test1=$(python3 ./test1.pyc)
		if [ $test1 == 1 ]; then
			n=$(md5sum <<< "$Student"); 
			id=$((0x${n%% *}))
			id=${id::-4}
		
			echo "WORKED:${Student} ${id}"
		else
			echo "TEST FAILED: Test GMR_test2.py failed."
		fi
	else
		echo "TEST FAILED: Test GMR_test1.py failed."
	fi
else
	echo "TEST FAILED: The python filename is incorrect. Change the filename to that specified."
	exit 
fi