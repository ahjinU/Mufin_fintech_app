'use client';

import React, { useEffect, useState } from 'react';
import Day from './Day';
import useDate from '@/utils/date';
import BookApis from '../_apis';
import { format } from 'date-fns';
import useBookStore from '../_store';

const DayList = ({ list }: { list: Date[] }) => {
  const { calculateDateRange } = useDate();
  const { currentMonth } = useBookStore();
  const { startDate, endDate } = calculateDateRange();

  const { getMonthBook } = BookApis();

  const [bookDetail, setBookDetail] = useState<DayDetail[] | null>(null);

  useEffect(() => {
    (async function () {
      const res = await getMonthBook({
        startDate: format(startDate, 'yyyy-MM-dd'),
        endDate: format(endDate, 'yyyy-MM-dd'),
        childUuid: null,
      });
      !bookDetail && setBookDetail(res?.data?.dayDetailList);
    })();
  }, [startDate, endDate]);

  const cal: Date[][] = [];
  let num = 0;

  for (let i = 0; i < list.length; i++) {
    i % 7 === 0 && cal.push([]);
    cal[cal.length - 1].push(list[i]);
  }

  return (
    <div className="px-[1.2rem] flex flex-col w-full items-center">
      {cal?.map((dayRow, rowIndex) => (
        <div
          className="flex flex-row w-full h-[4.5rem]"
          key={`cal-${rowIndex}`}
        >
          {dayRow?.map((day, colIndex) => {
            for (let ind = num; ind < (bookDetail?.length || 0); ind++) {
              if (
                (bookDetail && bookDetail[ind]?.date) ===
                format(day, 'yyyy-MM-dd')
              ) {
                num = ind;
                return (
                  <Day
                    day={day}
                    index={colIndex}
                    key={`day-${rowIndex}-${colIndex}`}
                    incomeDay={bookDetail && bookDetail[ind]?.incomeDay}
                    outcomeDay={bookDetail && bookDetail[ind]?.outcomeDay}
                  />
                );
              }
            }
            return (
              <Day
                day={day}
                index={colIndex}
                key={`day-${rowIndex}-${colIndex}`}
              />
            );
          })}
        </div>
      ))}
    </div>
  );
};

export default DayList;
