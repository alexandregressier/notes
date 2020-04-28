cmake_minimum_required(VERSION 3.16)
project(c_google_test C)

set(CMAKE_C_STANDARD 11)
set(This Example)

enable_testing()

add_subdirectory(googletest)

set(
        Headers
        example.h
)
set(
        Sources
        example.c
)
add_library(${This} STATIC ${Sources} ${Headers} example.c)
add_subdirectory(test)
