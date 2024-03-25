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
import ListDeal from './ListDeal';
import Link from 'next/link';
import { useState } from 'react';

export default function BookList() {
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
          <Link href={'./post'}>
            <TinyButton label={'직접 추가'} />
          </Link>
        </div>
        <Link className="cursor-pointer" href="./">
          <FlexBox
            isDivided={false}
            mode="LIST"
            topChildren={
              <OtherInfoElement
                leftExplainText={'화장품'}
                leftHighlightText={'씨제이올리브영 주식회사'}
                money={`${commaNum(3000)}원`}
                state={'UP'}
              />
            }
          />
        </Link>
        <Link className="cursor-pointer" href="./">
          <FlexBox
            isDivided={true}
            mode="LIST"
            topChildren={
              <div>
                <OtherInfoElement
                  leftExplainText={'화장품'}
                  leftHighlightText={'씨제이올리브영 주식회사'}
                  money={`${commaNum(3000)}원`}
                  state={'DOWN'}
                />
                <ListDeal
                  deals={[
                    {
                      totalPrice: 10000,
                      count: 1,
                      name: '씨제이올리브영 주식회사',
                    },
                    {
                      totalPrice: 10000,
                      count: 1,
                      name: '씨제이올리브영 주식회사',
                    },
                  ]}
                />
              </div>
            }
            bottomChildren={<p className="mt-[-1rem]">{'진아한테 갚아야됨'}</p>}
          />
        </Link>
      </div>
    </div>
  );
}
