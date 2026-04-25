#!/bin/bash

export PJ_NAME=$(basename $(pwd))

export script_path=../ecuacion-build-tools/client-tools/update-base-source.sh
${script_path} "${PJ_NAME}"
