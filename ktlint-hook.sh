#!/bin/sh

# Exit immediately if a command exits with a non-zero status
set -euo pipefail

echo "🧹 Running ktlint..."

# Detect root directory of git repo
PROJECT_ROOT="$(git rev-parse --show-toplevel)"
cd "$PROJECT_ROOT" || exit 1

# Path to your ktlint binary
KTLINT="./ktlint"  # Make sure ktlint is in root and executable

# Get list of staged Kotlin files
STAGED_FILES=$(git diff --cached --name-only --diff-filter=d | grep -E '\.kt$|\.kts$' || true)

if [ -z "$STAGED_FILES" ]; then
  echo "No Kotlin files to lint."
  exit 0
fi

echo "Linting the following files:"
echo "$STAGED_FILES"

ERROR_FILES=""

# Run ktlint on each file
for FILE in $STAGED_FILES; do
  $KTLINT -F "$FILE" || ERROR_FILES="$ERROR_FILES $FILE"
done

# If ktlint failed on any files
if [ -n "$ERROR_FILES" ]; then
  echo "❌ ktlint found issues in the following files:"
  for FILE in $ERROR_FILES; do
    echo "- $FILE"
  done
  echo "Please fix the issues before committing."
  exit 1
fi

# Re-add the formatted files to the commit
echo "$STAGED_FILES" | xargs git add

echo "✅ ktlint completed successfully. Changes added to the commit."
exit 0