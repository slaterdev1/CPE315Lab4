make

echo "Running Test 1"
java lab3 ../tst/tst1.asm ../tst/tst1Script > myT1Out
diff -w -B myT1Out ../tst/tst1Out --suppress-common-lines

echo "Running Test 2"
java lab3 ../tst/tst2.asm ../tst/tst2Script > myT2Out
diff -w -B myT2Out ../tst/tst2Out --suppress-common-lines

echo "Running Test 3"
java lab3 ../tst/tst3.asm ../tst/tst3Script > myT3Out
diff -w -B myT3Out ../tst/tst3Out --suppress-common-lines
