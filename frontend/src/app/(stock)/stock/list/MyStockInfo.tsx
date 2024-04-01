'use client';

import { useRouter } from 'next/navigation';
import { SmallButton } from '@/components';
import { StockInfo } from '../_types';
import { commaNum } from '@/utils/commaNum';
import { toCompanyEnglishName } from '../../company/_utils';

export default function MyStockInfo({ stock }: { stock: StockInfo }) {
  const { totalPriceAvg, totalPriceCur, priceAvg, priceCur, name } = stock;
  const router = useRouter();

  return (
    <>
      <div className="grid grid-cols-2 gap-x-[2rem] gap-y-[0rem]">
        <div className="flex flex-row justify-between w-full items-center">
          <p className="custom-light-text text-custom-dark-gray">
            총 주식 평가
          </p>
          <p className="custom-medium-text  text-custom-black">
            {commaNum(totalPriceCur)}
          </p>
        </div>
        <div className="flex flex-row justify-between w-full items-center">
          <p className="custom-light-text text-custom-dark-gray">현재가</p>
          <p className="custom-medium-text  text-custom-black">
            {commaNum(priceCur)}
          </p>
        </div>
        <div className="flex flex-row justify-between w-full items-center">
          <p className="custom-light-text text-custom-dark-gray">
            총 매수 초콜릿
          </p>
          <p className="custom-medium-text  text-custom-black">
            {commaNum(totalPriceAvg)}
          </p>
        </div>
        <div className="flex flex-row justify-between w-full items-center">
          <p className="custom-light-text text-custom-dark-gray">
            매수 평균 단가
          </p>
          <p className="custom-medium-text  text-custom-black">
            {commaNum(priceAvg)}
          </p>
        </div>
      </div>
      <div className="flex flex-row w-full gap-[1rem] justify-center">
        <SmallButton
          mode={'ACTIVE'}
          label={'더 사기'}
          handleClick={() =>
            router.push(`/company/buy/${toCompanyEnglishName(name)}`)
          }
        />
        <SmallButton
          mode={'ACTIVE'}
          label={'팔기'}
          handleClick={() =>
            router.push(`/company/sell/${toCompanyEnglishName(name)}`)
          }
        />
      </div>
    </>
  );
}
