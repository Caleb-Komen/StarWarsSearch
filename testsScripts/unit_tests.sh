print_red(){
  printf "\e[1;31m$1\e[0m"
}

print_green(){
    printf "\e[1;32m$1\e[0m"
}

print_yellow(){
    printf "\e[1;33m$1\e[0m"
}

print_blue(){
    printf "\e[1;34m$1\e[0m"
}

print_blue "\nStarting..."

cd "/home/caleb/AndroidStudioProjects/StarWarsSearch/"

print_yellow "\n\nRunning unit tests...\n"

./gradlew data:test
./gradlew domain:test
./gradlew app:test

print_green "\nUnit tests COMPLETE.\n"