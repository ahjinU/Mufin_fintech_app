'use client';

import React, { useEffect, useState } from 'react';
import Day from './Day';
import useDate from '@/utils/date';
import BookApis from '../_apis';
import { format } from 'date-fns';
import useBookStore from '../_store';

const DayList = ({ list }: { list: Date[] }) => {
  const { calculateDateRange } = useDate();
  const { startDate, endDate } = calculateDateRange();
  const { currentEndDate, currentStartDate } = useBookStore();

  const { getMonthBook } = BookApis();

  const [bookList, setBookList] = useState<DayDetail[] | null>(null);

  useEffect(() => {
    (async function () {
      const res = await getMonthBook({
        startDate: format(currentStartDate, 'yyyy-MM-dd'),
        endDate: format(currentEndDate, 'yyyy-MM-dd'),
        childUuid: null,
      });
      setBookList(res?.data?.dayDetailList);
    })();
  }, [currentStartDate, currentEndDate]);

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
            for (let ind = num; ind < (bookList?.length || 0); ind++) {
              if (
                (bookList && bookList[ind]?.date) === format(day, 'yyyy-MM-dd')
              ) {
                num = ind;
                return (
                  <Day
                    day={day}
                    index={colIndex}
                    key={`day-${rowIndex}-${colIndex}`}
                    incomeDay={bookList && bookList[ind]?.incomeDay}
                    outcomeDay={bookList && bookList[ind]?.outcomeDay}
                    loanPaymentDay={bookList && bookList[ind]?.loanPaymentDay}
                    savingsDay={bookList && bookList[ind]?.savingsDay}
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
