rem # Adds the files in the local repository and stages them for commit. To unstage a file, use 'git reset HEAD YOUR-FILE'.
git add .

rem # Commits the tracked changes and prepares them to be pushed to a remote repository.
rem # To remove this commit and modify the file, use 'git reset --soft HEAD~1' and commit and add the file again.
git commit -m "Auto Commit"

rem # Verifies the new remote URL
git remote -v

rem # Pushes the changes in your local repository up to the remote repository you specified as the origin
git push -q -u origin master
