'use client';

import { BackButton, Header, Accordion } from '@/components';
import { useState, useEffect } from 'react';
import { ChildrenSavingsStateListType } from '../../_types';
import SavingsApis from '../../_apis';
import { commaNum } from '@/utils/commaNum';

export default function ConfirmSavings() {
  const [childrenSavingsStateList, setChildrenSavingsStateList] = useState<
    ChildrenSavingsStateListType[]
  >([]);
  const { getChildrenSavingsState } = SavingsApis();

  useEffect(() => {
    (async () => {
      const childrenSavingsStateListData = await getChildrenSavingsState();
      setChildrenSavingsStateList(
        childrenSavingsStateListData?.data?.savingsDetailListByChild,
      );
    })();
  }, []);

  return (
    <>
      <Header>
        <BackButton label="적금 현황 확인하기" />
      </Header>
      <section className="w-full p-[1.2rem] flex flex-col gap-[1rem] relative min-h-screen">
        {childrenSavingsStateList && childrenSavingsStateList.length === 0 ? (
          <p className="custom-semibold-text text-custom-medium-gray mx-auto  mt-[2rem]">
            아이가 가입한 적금이 없습니다.
          </p>
        ) : (
          childrenSavingsStateList.map((childrenSavingsState, index) => {
            return childrenSavingsState.savingsDetailList.map(
              (savingsDetail, index2) => {
                return (
                  <Accordion
                    key={`childrenSavingsState-${index}-${index2}`}
                    contents={[
                      [
                        '적금 가입일',
                        `${new Date(savingsDetail.createdAt).getFullYear()}.${
                          new Date(savingsDetail.createdAt).getMonth() + 1
                        }.${new Date(savingsDetail.createdAt).getDate()}`,
                      ],
                      [
                        '적금 만기일',
                        `${new Date(savingsDetail.expiredAt).getFullYear()}.${
                          new Date(savingsDetail.expiredAt).getMonth() + 1
                        }.${new Date(savingsDetail.expiredAt).getDate()}`,
                      ],
                      [
                        '현재까지 이체금액',
                        `${commaNum(savingsDetail.balance)}원`,
                      ],
                      ['적금 이자율', `${savingsDetail.interest}%`],
                      [
                        '받아간 이자',
                        `${
                          savingsDetail.interestAmount
                            ? commaNum(savingsDetail.interestAmount)
                            : 0
                        }원`,
                      ],
                    ]}
                    mode={
                      savingsDetail.state === '정상'
                        ? 'NORMAL'
                        : savingsDetail.state === '만기'
                        ? 'COMPLETED'
                        : 'EXCEPTIONAL'
                    }
                    name={childrenSavingsState.userName}
                    title={
                      savingsDetail.state === '정상'
                        ? ' 는 순조롭게 적금을 드는 중...'
                        : savingsDetail.state === '만기'
                        ? ' 는 만기에 도달해서 이자를 받아갔어요 :)'
                        : ' 는 적금을 중도에 해지했어요 :('
                    }
                  />
                );
              },
            );
          })
        )}
      </section>
    </>
  );
}
