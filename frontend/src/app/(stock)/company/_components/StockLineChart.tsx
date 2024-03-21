'use client';

import dynamic from 'next/dynamic';
const ApexChart = dynamic(() => import('react-apexcharts'), { ssr: false });

const smooth: 'smooth' = 'smooth';

interface StockData {
  name: string;
  data: number[];
}

const series: StockData[] = [
  {
    name: '바람막이 회사',
    data: [
      30, 32, 31, 35, 36, 39, 40, 42, 40, 40, 39, 38, 40, 42, 45, 49, 50, 54,
      52, 51, 45, 40, 35, 38, 40, 41, 40, 40, 38, 35, 31, 28, 27, 29, 30, 35,
      45, 48, 50, 52, 55, 56, 57, 58, 59, 60, 59, 55, 48, 49, 47, 46, 48, 49,
      50, 51, 52, 53, 55, 60,
    ],
    // data: Array.from({ length: 180 }, (v, i) => i),
  },
];

const maxValueIndex = series[0].data.findIndex(
  (num) => num === Math.max(...series[0].data),
);
const minValueIndex = series[0].data.findIndex(
  (num) => num === Math.min(...series[0].data),
);

export function StockLineChart() {
  const option = {
    chart: {
      id: 'stockLineChart',
      toolbar: {
        show: false,
      },
      fontFamily: 'pretendard, sans-serif',
      animations: {
        enabled: false,
      },
    },
    dataLabels: {
      enabled: true,
      style: {
        fontSize: '10px',
      },
      background: {
        enabled: false,
      },
      offsetX: -13,
      formatter: (
        value: number,
        { dataPointIndex, w }: { dataPointIndex: number; w: any },
      ) => {
        if (
          value === series[0].data[minValueIndex] &&
          dataPointIndex === minValueIndex
        ) {
          console.log(w);
          return '최저 ' + value;
        } else if (
          value === series[0].data[maxValueIndex] &&
          dataPointIndex === maxValueIndex
        ) {
          return '최고 ' + value;
        } else {
          return '';
        }
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
      show: false,
      min: Math.min(...series[0].data) - 5,
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
      min: -5,
      max: 65,
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
  };

  return (
    <section className="mx-auto p-[1rem]">
      <ApexChart
        type="line"
        options={option}
        series={series}
        height={300}
        width={310}
      />
    </section>
  );
}
