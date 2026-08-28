#!/bin/sh
set -eou pipefail
include_files=$(git diff --name-only --cached | xargs -I {} echo "--include {}" | tr '\n' ' ')
semgrep_output=$(semgrep ci --dry-run --text -q --json-output="semgrep.json" $include_files)
# Check if there are any findings
if [ -n "$semgrep_output" ]; then
    echo "$semgrep_output"
    exit 1
else
    echo "No issues found by Semgrep."
    exit 0
fi