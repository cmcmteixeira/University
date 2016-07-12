cmake_minimum_required(VERSION 3.2)
project(sorting C)


set(ENV{PKG_CONFIG_PATH} "")
find_package(PkgConfig REQUIRED)

pkg_search_module(MONGO REQUIRED mongoc)

include_directories(${MONGO_INCLUDE_DIRS})
include_directories(${MONGO_LIBRARY_DIRS})

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")
set(SOURCE_FILES main.c)

add_executable(sorting ${SOURCE_FILES} config.h log.h)
target_link_libraries(sorting ${MONGO_LIBRARIES} )
