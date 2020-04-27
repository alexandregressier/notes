#include <stdio.h>
#include <stdbool.h>

int main(int argc, char** argv) {
    puts("Hello, world!");

    for (int i = 0; i < argc; ++i)
        puts(argv[i]);

    bool flag = true;
    if (flag)
        puts("Yes");
    else
        puts("No");

    return 0;
}
