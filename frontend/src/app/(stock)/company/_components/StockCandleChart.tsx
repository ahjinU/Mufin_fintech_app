'use client';

import dynamic from 'next/dynamic';
import { useState, useEffect, useRef } from 'react';
import SockJS from 'sockjs-client';
import { Stomp, CompatClient } from '@stomp/stompjs';
const ApexChart = dynamic(() => import('react-apexcharts'), { ssr: false });
import { Tag } from '@/components';
import { series } from './data';
import { getMaxMinValueIndex } from '../_utils';

const datetime: 'datetime' = 'datetime';
const pan: 'pan' = 'pan';

interface StockCandleData {
  x: number;
  y: number[];
}

export function StockCandleChart() {
  // y는 [시가, 고가, 저가, 종가]
  const [data, setData] = useState<StockCandleData[]>([
    { x: Date.now(), y: [0, 0, 0, 0] },
  ]);
  const client = useRef<CompatClient | null>(null);

  const connectHandler = () => {
    const socket = new SockJS('https://mufin.life/ws-connection');
    client.current = Stomp.over(socket);

    client.current.connect({}, () => {
      client.current?.subscribe(`/sub/orders/`, (message) => {
        console.log(message);

        // 기존 봉 차트에 데이터 추가
        // setData((prev) => {
        //   return prev
        //     ? {...prev, JSON.parse(message.body)}
        //     : null;
        // });
      });
    });
  };

  useEffect(() => {
    connectHandler();
  }, []);

  const option = {
    chart: {
      height: 290,
      id: 'stockCandleChart',
      fontFamily: 'pretendard, sans-serif',
      toolbar: {
        autoSelected: pan,
        show: false,
      },
      zoom: {
        enabled: true,
      },
      animations: {
        enabled: false,
      },
    },
    plotOptions: {
      candlestick: {
        colors: {
          upward: '#cd2626',
          downward: '#5969ff',
        },
      },
    },
    grid: {
      show: true,
    },
    xaxis: {
      type: datetime,
      range: 1000 * 60 * 60 * 24 * 21,
      labels: {
        show: false,
        format: 'dd일',
        style: {
          colors: '#d4d4d8',
          fontSize: '10px',
        },
      },
      axisBorder: { show: false },
      axisTicks: { show: false },
      tooltip: {
        enabled: false,
      },
    },
    yaxis: {
      opposite: true,
      show: true,
      tickAmount: 3,
      labels: {
        style: {
          colors: '#d4d4d8',
          fontSize: '10px',
        },
        formatter: (value: number) => value.toFixed(0),
        offsetX: -5,
      },
    },
    annotations: {
      yaxis: [
        {
          y: getMaxMinValueIndex(series)[0],
          borderColor: '#cd2626',
          label: {
            borderColor: '#cd2626',
            style: {
              color: '#fff',
              background: '#cd2626',
            },
            text: `최고: ${getMaxMinValueIndex(series)[0]}`,
            offsetX: -6,
          },
        },
        {
          y: getMaxMinValueIndex(series)[1],
          borderColor: '#5969ff',
          label: {
            borderColor: '#5969ff',
            style: {
              color: '#fff',
              background: '#5969ff',
            },
            text: `최저: ${getMaxMinValueIndex(series)[1]}`,
            offsetX: -6,
            offsetY: 19,
          },
        },
        {
          y: series[0].data[series[0].data.length - 1].y[3],
          borderColor: '#0be881',
          label: {
            borderColor: '#0be881',
            style: {
              color: '#fff',
              background: '#0be881',
            },
          },
        },
      ],
    },
    tooltip: {
      custom: ({ w, dataPointIndex }: { w: any; dataPointIndex: number }) => {
        return (
          "<article class='tooltip'>" +
          `<span class='tooltip-date'>${w.config.series[0].data[
            dataPointIndex
          ].x.getFullYear()}년 ${
            w.config.series[0].data[dataPointIndex].x.getMonth() + 1
          }월 ${w.config.series[0].data[dataPointIndex].x.getDate()}일</span>` +
          `<span>시가: <strong class='tooltip-bold'>${w.config.series[0].data[
            dataPointIndex
          ].y[0].toFixed(0)}초코칩</strong></span>` +
          `<span class='tooltip-blue'>저가: <strong class='tooltip-bold'>${w.config.series[0].data[
            dataPointIndex
          ].y[2].toFixed(0)}초코칩</strong></span>` +
          `<span class='tooltip-red'>고가: <strong class='tooltip-bold'>${w.config.series[0].data[
            dataPointIndex
          ].y[1].toFixed(0)}초코칩</strong></span>` +
          `<span>종가: <strong class='tooltip-bold'>${w.config.series[0].data[
            dataPointIndex
          ].y[3].toFixed(0)}초코칩</strong></span>` +
          '</article>'
        );
      },
    },
  };

  return (
    <section className="mx-auto pt-[1rem]">
      <ApexChart
        type="candlestick"
        options={option}
        series={series}
        height={300}
        width={336}
      />

      <Tag
        tags={[
          {
            label: '1일',
            onClick: () => {},
          },
          {
            label: '4일',
            onClick: () => {},
          },
          {
            label: '1주',
            onClick: () => {},
          },
        ]}
      />
    </section>
  );
}
