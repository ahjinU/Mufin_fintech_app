'use client';

import {
  ComplexInput,
  FlexBox,
  Header,
  MoneyShow,
  OtherInfoElement,
  Tag,
} from '@/components';
import Calendar from './_components/Calendar';
import { commaNum } from '@/utils/commaNum';
import { format, getDay, getMonth } from 'date-fns';
import { useEffect, useState } from 'react';
import BookApis from './_apis';
import { getKorDay } from '@/utils/getKorDay';
import useBookStore from './_store';
import useUserStore from '@/app/_store/store';
import { TagType } from '@/components/Tag/Tag';

export default function Book() {
  const {
    currentStartDate,
    currentEndDate,
    updateChildren,
    children,
    curChild,
    updateCurChild,
  } = useBookStore();
  const [tags, SetTags] = useState<TagType[]>([]);
  const { getMonthBookDetail, getMonthBook } = BookApis();
  const { userData } = useUserStore();

  const [bookDetail, setBookDetail] = useState<MonthBookDetailType | null>(
    null,
  );

  useEffect(() => {
    let childUuid: string | null = null;
    userData.isParent &&
      tags.length === 0 &&
      (async function () {
        const res = await getMonthBook({
          startDate: format(currentStartDate, 'yyyy-MM-dd'),
          endDate: format(currentEndDate, 'yyyy-MM-dd'),
          childUuid: null,
        });
        if (!curChild) childUuid = res?.data?.childs[0].childUuid;
        updateChildren(res?.data?.childs);
        const newData: TagType[] = [];
        res?.data?.childs?.map((child: childType, index: number) => {
          newData.push({
            label: child.name,
            onClick: () => {
              updateCurChild({
                childUuid: child.childUuid,
                name: child.name,
                index: index,
              });
            },
          });
        });
        SetTags(newData);
        !curChild && updateCurChild(res?.data?.childs[0]);
      })();
  }, []);

  useEffect(() => {
    (async function () {
      const res = await getMonthBookDetail({
        startDate: format(currentStartDate, 'yyyy-MM-dd'),
        endDate: format(currentEndDate, 'yyyy-MM-dd'),
        childUuid: curChild.childUuid || null,
      });
      setBookDetail(res?.data);
    })();
  }, [curChild, currentEndDate, currentStartDate]);

  return (
    <div>
      <Header>
        <h1 className="custom-bold-text">용돈 가계부</h1>
      </Header>
      <div className="p-[1.2rem] flex flex-col gap-[1rem]">
        {userData.isParent && tags && (
          <div className="flex flex-row gap-[1rem] mt-[-2rem] mb-[1.5rem]">
            <Tag tags={tags} index={curChild.index || 0} />
          </div>
        )}
        <div className="flex flex-col items-end w-full mb-[0.5rem] mt-[-2rem] text-[0.9rem] leading-[1r.2em]">
          <p>대출, 적금 개설할 때 입금하기로 약속한 날짜에요!</p>
          <p>
            <span className="text-[green] text-[0.8rem]">●</span> 대출 상환
            예정일{' '}
            <span className="text-[#f0cc5f] text-[0.8rem] ml-[0.7rem]">●</span>{' '}
            적금 입금 예정일
          </p>
        </div>
        <Calendar />
        <ComplexInput label={'이번 달 내역'} mode={'NONE'}>
          <MoneyShow
            mode={'DIVIDED'}
            text={['지출', '수입']}
            money={[
              bookDetail?.monthOutcome
                ? commaNum(bookDetail?.monthOutcome)
                : '0',
              bookDetail?.monthIncome
                ? `+${commaNum(bookDetail?.monthIncome)}`
                : '0',
            ]}
            unit={'원'}
          />
        </ComplexInput>
        <div className="flex flex-col gap-[1.2rem]">
          {bookDetail?.transactionDtoList?.map((trans, index, array) => {
            const prev = index > 0 && array[index - 1];
            const date =
              index === 0 ||
              (prev &&
                format(prev?.date, 'yyyy-MM-dd') !==
                  format(trans?.date, 'yyyy-MM-dd'))
                ? `${format(trans?.date, 'd')}일 ${getKorDay(
                    getDay(trans?.date),
                  )}`
                : undefined;

            return (
              <div key={`tarns-${index}`}>
                {date && (
                  <div className="mb-[0.5rem] text-[1.2rem] font-[300] text-custom-medium-gray w-full border-b-[0.1rem]">
                    {date}
                  </div>
                )}
                <FlexBox
                  isDivided={false}
                  mode="NONE"
                  topChildren={
                    <OtherInfoElement
                      leftExplainText={trans?.code}
                      leftHighlightText={trans?.counterpartyName}
                      money={`${commaNum(trans?.amount)}원`}
                      state={trans.amount > 0 ? 'UP' : 'DOWN'}
                    />
                  }
                />
              </div>
            );
          })}
        </div>
      </div>
    </div>
  );
}
