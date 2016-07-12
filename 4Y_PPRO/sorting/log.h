//
// Created by ventureoak on 18-06-2015.
//

#ifndef SORTING_LOG_H
#define SORTING_LOG_H

#include <time.h>
#include <stdio.h>
#include <string.h>

 void debug_print(char *fmt, ...) {
    va_list arg_ptr;
    va_start(arg_ptr, fmt);
    char time_str[30];
    time_t now = time(NULL);
    strftime(time_str, 20, "%Y/%m/%d %H:%M:%S", localtime(&now));

    char partial_1[256];
    vsprintf(partial_1,fmt,arg_ptr);
    printf("**[DailyNews]**: %s - %s \n",time_str,partial_1);
    va_end(arg_ptr);

}

#endif //SORTING_LOG_H

