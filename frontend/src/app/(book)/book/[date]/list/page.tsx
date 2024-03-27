'use client';

import {
  AdBox,
  BackButton,
  FlexBox,
  Header,
  Input,
  MoneyShow,
  OtherInfoElement,
  TinyButton,
} from '@/components';
import { commaNum } from '@/utils/commaNum';
import ListDeal from '../ListDeal';
import Link from 'next/link';
export default function BookList() {
  const details = [
    {
      storeName: '상점 A',
      transactionUUID: 'uuid1',
      type: '현금',
      amount: 50000,
      receptDetails: [
        {
          item: '상품 A',
          cnt: 2,
          price: 10000,
          total: 20000,
        },
        {
          item: '상품 B',
          cnt: 1,
          price: 20000,
          total: 20000,
        },
      ],
      content: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.',
      category: '식료품',
    },
    {
      storeName: '상점 B',
      transactionUUID: 'uuid2',
      type: '계좌',
      amount: 80000,
      receptDetails: [
        {
          item: '상품 C',
          cnt: 1,
          price: 30000,
          total: 30000,
        },
        {
          item: '상품 D',
          cnt: 3,
          price: 15000,
          total: 45000,
        },
      ],
      content:
        'Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
      category: '의류',
    },
  ];
  return (
    <div>
      <div className="p-[1.2rem] flex flex-col gap-[1rem]">
        <AdBox
          mode={'STATIC'}
          subText={'상세 정보 추가 또는 더치페이 요청을 할 수 있어요!'}
          title={'각 지출 내역을 누르면 선택할 수 있어요 '}
        />
        <MoneyShow
          mode={'DIVIDED'}
          text={['지출', '수입']}
          money={[`${commaNum(-6000)}`, `${commaNum(5000)}`]}
          unit={'원'}
        />
        <div className="w-full flex items-end justify-end pr-[1rem]">
          <Link href={`./post`}>
            <TinyButton label={'직접 추가'} />
          </Link>
        </div>
        {details.map((deal, index) => (
          <Link
            className="cursor-pointer"
            href={`./${deal.transactionUUID}/detail`}
            key={`deal-${index}`}
          >
            <FlexBox
              isDivided={deal.content ? true : false}
              mode="LIST"
              topChildren={
                <div>
                  <OtherInfoElement
                    leftExplainText={`${deal.category}`}
                    leftHighlightText={`${deal.storeName}`}
                    money={`${commaNum(deal.amount)}원`}
                    state={`${deal.amount > 0 ? 'UP' : 'DOWN'}`}
                  />
                  {deal.receptDetails && (
                    <ListDeal deals={deal.receptDetails} />
                  )}
                </div>
              }
              bottomChildren={
                <>
                  {deal.content && (
                    <p className="mt-[-1rem]">{`${deal.content}`}</p>
                  )}
                </>
              }
            />
          </Link>
        ))}
      </div>
    </div>
  );
}
