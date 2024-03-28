'use client';

import dynamic from 'next/dynamic';
import { useState, useEffect } from 'react';
import { Tag } from '@/components';
import { getStockLineData } from '../_apis';
const ApexChart = dynamic(() => import('react-apexcharts'), { ssr: false });

const smooth: 'smooth' = 'smooth';
const easeout: 'easeout' = 'easeout';

interface StockData {
  date: Date;
  price: number;
}

export function StockLineChart() {
  // ApexCharts에 넘겨야 하는 series형식은 배열 내에 하나의 객체를 넘기는 법
  // 서버에서 date도 넘겨주지만 필요 없음.
  const [data, setData] = useState<number[]>([0]);
  const [period, setPeriod] = useState<number>(30);
  const maxValueIndex = data.findIndex((num) => num === Math.max(...data));
  const minValueIndex = data.findIndex((num) => num === Math.min(...data));

  useEffect(() => {
    (async function () {
      const data = await getStockLineData('바람막이', period);
      const prices = data.data.map((dataObj: StockData) => dataObj.price);
      setData(prices);
    })();
  }, [period]);

  const option = {
    chart: {
      id: 'stockLineChart',
      toolbar: {
        show: false,
      },
      fontFamily: 'pretendard, sans-serif',
      animations: {
        easing: easeout,
        dynamicAnimation: {
          enabled: true,
          speed: 600,
        },
      },
      zoom: {
        enabled: false,
      },
    },
    stroke: {
      curve: smooth,
      width: 2,
    },
    markers: {
      colors: '#0be881',
    },
    fill: {
      type: 'gradient',
      gradient: { gradientToColors: ['#0be881'], stops: [0, 100] },
    },
    grid: { show: false },
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
      min: Math.min(...data),
    },
    xaxis: {
      labels: {
        show: false,
      },
      axisBorder: { show: false },
      axisTicks: { show: false },
      tooltip: {
        enabled: false,
      },
      min: 0,
      max: period,
    },
    tooltip: {
      x: {
        show: false,
      },
      y: {
        formatter: (value: number) => value + '초코칩',
      },
      marker: {
        show: false,
      },
    },
    annotations: {
      yaxis: [
        {
          y: data[maxValueIndex],
          borderColor: '#cd2626',
          label: {
            borderColor: '#cd2626',
            style: {
              color: '#fff',
              background: '#cd2626',
            },
            text: `최고: ${data[maxValueIndex]}`,
            offsetX: -6,
          },
        },
        {
          y: data[minValueIndex],
          borderColor: '#5969ff',
          label: {
            borderColor: '#5969ff',
            style: {
              color: '#fff',
              background: '#5969ff',
            },
            text: `최저: ${data[minValueIndex]}`,
            offsetX: -6,
            offsetY: 19,
          },
        },
      ],
    },
  };

  return (
    <section className="mx-auto pt-[1rem]">
      <ApexChart
        type="line"
        options={option}
        series={[{ name: '바람막이', data }]}
        height={300}
        width={336}
      />

      <Tag
        tags={[
          {
            label: '1개월',
            onClick: () => setPeriod(30),
          },
          {
            label: '2개월',
            onClick: () => setPeriod(60),
          },
        ]}
      />
    </section>
  );
}