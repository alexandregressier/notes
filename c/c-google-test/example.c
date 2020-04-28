/*
 * Notes:
 * - It is pretty common to test C code using a C++ testing frameworks
 * - The YouTube user `vector-of-bool` is also the author of the `CMake Tools`
 * extension for Visual Studio Code
 * - When running tests, Google Test becomes the main programs & it pulls your
 * code as a library
 *
 * 1. Clone the Google Test repository in the current directory:
 * $ git clone -b master --recursive git@github.com:google/googletest.git
 *
 * Note: This directory should not be version controlled automatically
 *
 * In CMake:
 * - `add_executable()` defines a library
 * - `add_library()` defines a library
 *
 * 2. Create the root `CMakeLists.txt` file
 * - add_subdirectory(googletest)
 * - Specify `enable_testing()`
 * - add_subdirectory(test)
 *
 * 3. Create the test/ directory & its `CMakeLists.txt` file
 * - No need for headers
 * - `target_link_libraries` to list the things you depend on
 *   - `PUBLIC` means you + anything using this program would depend on what is
 *   listed
 * - `gtest_main` is provided by Google Test & pulls in the `main` program
 * - + what you are testing (here `Example`)
 * - `add_test` specifies that this program is unit tests
 *
 *
 */
#include "example.h"

#include <stdio.h>

void hello(void) {
    printf("Hello, World!\n");
}
