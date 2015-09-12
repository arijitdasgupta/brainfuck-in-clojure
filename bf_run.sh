#!/bin/bash

file="code_n_input.txt"
if [ -f "$file" ]
then
	lein run < code_n_input.txt
else
	cp sample_code_n_input.txt code_n_input.txt
  lein run < code_n_input.txt
fi
