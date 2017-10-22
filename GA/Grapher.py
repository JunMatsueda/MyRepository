#! /usr/bin/env python
# -*- coding: utf8 -*-

"""
csvファイルをプロットしグラフ化するプログラム
"""
import os
from matplotlib import pyplot

def main():
    """グラフ化のメイン関数"""

    current_directory = os.getcwd()
    a_file = current_directory + os.sep + 'allEV.csv'
    figure, (graph1, graph2) = pyplot.subplots(ncols=2, figsize=(10, 4))
    plot_data(graph1, a_file)
    a_file = current_directory + os.sep + 'good.csv'
    plot_data(graph2, a_file)

    pyplot.show()

    return

def plot_data(a_graph, a_file):
    """ファイルの内容を取り込み、座標をプロットする関数"""

    a_list = []
    with open(a_file, 'r') as this_file:                 # オープン
        for a_string in this_file:                       # 一行一行、順々に読み込む
            a_list.append(a_string)


    split_list = []
    for a_string in a_list:
        split_list.append(a_string.split(", \ "))

    data_list = []
    for a_string in split_list:
        data_list.append(a_string[0].split(","))

    x_position = []
    y1_position = []
    y2_position = []
    list_size = len(data_list[0])
    if list_size == 2:
        for a_list in data_list:
            if a_list[0].isdigit():
                x_point = int(a_list[0])
                y_point = float(a_list[1])
                x_position.append(x_point)
                y1_position.append(y_point)

        a_graph.plot(x_position, y1_position, ',')
        a_graph.set_ylabel("Evaluation")
        a_graph.set_xlabel("Individual")
    else:
        for a_list in data_list:
            if a_list[0].isdigit():
                x_point = int(a_list[0])
                y1_point = float(a_list[1])
                y2_point = float(a_list[2])
                x_position.append(x_point)
                y1_position.append(y1_point)
                y2_position.append(y2_point)

        a_graph.plot(x_position, y1_position, label='best')
        a_graph.plot(x_position, y2_position, 'r', label='averrage')
        a_graph.set_ylabel("Evaluation")
        a_graph.set_xlabel("Generation")


    a_graph.set_yscale("log")

if __name__ == '__main__':
    import sys
    sys.exit(main())
