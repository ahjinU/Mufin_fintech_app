'use client';

import {
  AdBox,
  ComplexInput,
  FlexBox,
  MoneyInfoElement,
  MoneyShow,
  OtherInfoElement,
  TinyButton,
} from '@/components';
import { commaNum } from '@/utils/commaNum';
import Link from 'next/link';
import ListDeal from '../_components/ListDeal';
import { useEffect, useState } from 'react';
import BookApis from '../../_apis';
import { format } from 'date-fns';
import useBookStore from '../../_store';
import Image from 'next/image';
import useUserStore from '@/app/_store/store';
export default function BookList() {
  const [dayData, setDayData] = useState<TransactionType[]>();
  const [totalIncome, setTotalIncome] = useState(0);
  const [totalOutcome, setTotalOutcome] = useState(0);
  const [loans, setLoans] = useState<LoanType[]>();
  const [savings, setSavings] = useState<SavingType[]>();
  const { selectedDate, updateSelectedTransaction, curChild } = useBookStore();
  const { userData } = useUserStore();

  const { getDayBook } = BookApis();

  useEffect(() => {
    selectedDate &&
      (async function () {
        const res = await getDayBook({
          date: format(selectedDate, 'yyyy-MM-dd'),
          childUuid: curChild.childUuid || null,
        });
        console.log(res);
        setLoans(res?.data?.loan);
        setSavings(res?.data?.savings);
        setTotalIncome(res?.data?.dayIncome);
        setTotalOutcome(res?.data?.dayOutcome);
        setDayData(res?.data?.transactionDetails);
      })();
  }, [selectedDate, curChild]);

  return (
    <div className="p-[1.2rem] flex flex-col gap-[1.5rem]">
      {!userData.isParent && (
        <AdBox
          mode={'STATIC'}
          subText={'영수증을 촬영해서 상세 정보를 추가해보세요!'}
          title={'각 지출 내역을 누르면 선택할 수 있어요 '}
        />
      )}
      <MoneyShow
        mode={'DIVIDED'}
        text={['지출', '수입']}
        money={[`${commaNum(totalOutcome)}`, `${commaNum(totalIncome)}`]}
        unit={'원'}
      />
      {!userData.isParent && (
        <div className="w-full flex items-end justify-end pr-[1rem]">
          <Link href={`./post`}>
            <TinyButton label={'지출 추가 +'} />
          </Link>
        </div>
      )}
      <div className="flex flex-col gap-[1rem]">
        {dayData?.map((deal, index) =>
          !deal.counterpartyName.startsWith('적금' || '대출') &&
          !userData.isParent ? (
            <Link
              className="cursor-pointer"
              href={`./${deal?.transactionUuid}/detail`}
              key={`deal-${index}`}
              onClick={() => updateSelectedTransaction(deal)}
            >
              <FlexBox
                isDivided={deal?.memo ? true : false}
                mode="LIST"
                topChildren={
                  <div>
                    <OtherInfoElement
                      leftExplainText={`${deal.code}`}
                      leftHighlightText={`${deal?.counterpartyName}`}
                      money={`${commaNum(deal.amount)}원`}
                      state={`${deal.amount > 0 ? 'UP' : 'DOWN'}`}
                    />
                    {deal.receipts && <ListDeal deals={deal.receipts} />}
                  </div>
                }
                bottomChildren={
                  <>
                    {deal.memo && (
                      <p className="mt-[-1rem]">{`${deal.memo}`}</p>
                    )}
                  </>
                }
              />
            </Link>
          ) : (
            <FlexBox
              key={`deal-noMove-${index}`}
              isDivided={deal?.memo ? true : false}
              mode="LIST"
              topChildren={
                <div>
                  <OtherInfoElement
                    leftExplainText={`${deal.code}`}
                    leftHighlightText={`${deal?.counterpartyName}`}
                    money={`${commaNum(deal.amount)}원`}
                    state={`${deal.amount > 0 ? 'UP' : 'DOWN'}`}
                  />
                  {deal.receipts && <ListDeal deals={deal.receipts} />}
                </div>
              }
              bottomChildren={
                <>
                  {deal.memo && <p className="mt-[-1rem]">{`${deal.memo}`}</p>}
                </>
              }
            />
          ),
        )}
      </div>
      <div className="flex flex-row items-end justify-end gap-[0.7rem] mb-[-3.5rem]">
        <div className="flex flex-row items-center gap-[0.2rem]">
          <Image
            src={'/images/icon-repay-smile.png'}
            width={200}
            height={200}
            alt={'정상 납부 중'}
            className="w-[1.5rem] h-[1.5rem]"
          />
          <p className="text-[1rem] font-[300] text-custom-dark-purple">
            정상 납부 중
          </p>
        </div>
        <div className="flex flex-row items-center gap-[0.2rem]">
          <Image
            src={'/images/icon-repay-sad.png'}
            width={200}
            height={200}
            alt={'연체 횟수 남음'}
            className="w-[1.5rem] h-[1.5rem]"
          />
          <p className="text-[1rem] font-[300] text-custom-red">
            연체 횟수 남음
          </p>
        </div>
      </div>
      <ComplexInput label={'오늘 입금 약속한 내 대출'} mode={'NONE'}>
        <div className="flex flex-col gap-[1rem]">
          {loans && loans?.length > 0 ? (
            loans?.map((loan, index) => {
              return (
                <FlexBox
                  key={`today-loan-${index}`}
                  isDivided={false}
                  topChildren={
                    <div className="my-[-1rem]">
                      <MoneyInfoElement
                        imageSrc={`${
                          loan.hasOverdue
                            ? '/images/icon-repay-sad.png'
                            : '/images/icon-repay-smile.png'
                        }`}
                        leftExplainText={loan?.name}
                        leftHighlightText={`${commaNum(
                          loan?.currentPaymentAmount,
                        )}원 납부/${commaNum(loan?.totalPaymentAmount)}원`}
                        buttonOption={'TINY_BUTTON'}
                        tinyButtonLabel="상환하기"
                        link={`${loan.loanUuid}/detail`}
                      />
                    </div>
                  }
                />
              );
            })
          ) : (
            <div className="custom-light-text text-center">
              입금 약속된 대출이 없어요
            </div>
          )}
        </div>
      </ComplexInput>
      <ComplexInput label={'오늘 입금 약속한 내 적금'} mode={'NONE'}>
        <div className="flex flex-col gap-[1rem]">
          {savings && savings.length > 0 ? (
            savings?.map((saving, index) => {
              return (
                <FlexBox
                  key={`today-loan-${index}`}
                  isDivided={false}
                  topChildren={
                    <div className="my-[-1rem]">
                      <MoneyInfoElement
                        imageSrc={`${
                          saving.hasOverdue
                            ? '/images/icon-repay-sad.png'
                            : '/images/icon-repay-smile.png'
                        }`}
                        leftExplainText={saving?.name}
                        leftHighlightText={`${commaNum(saving?.amount)}원`}
                        buttonOption={'TINY_BUTTON'}
                        tinyButtonLabel="납부하기"
                        link={`/savings/pay/${saving?.accountUuid}`}
                      />
                    </div>
                  }
                />
              );
            })
          ) : (
            <div className="custom-light-text text-center">
              입금 약속된 적금이 없어요
            </div>
          )}
        </div>
      </ComplexInput>
    </div>
  );
}
