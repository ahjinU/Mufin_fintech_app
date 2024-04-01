'use client';

import { FlexBox, TinyButton } from '@/components';
import { useState, useEffect } from 'react';
import { SavingsListType } from '../../_types';
import SavingsApis from '../../_apis';
import { useRouter } from 'next/navigation';

function Title({ name }: { name: string }) {
  return (
    <section>
      <h2 className="custom-medium-text">{name}</h2>
      <span className="custom-light-text">적금</span>
    </section>
  );
}

function ContentRow({ keyName, value }: { keyName: string; value: string }) {
  return (
    <section className="flex justify-between custom-medium-text">
      <span>{keyName}</span>
      <span>{value}</span>
    </section>
  );
}

export default function ApplySavings() {
  const [savingsList, setSavingsList] = useState<SavingsListType[]>([]);
  const { getSavingsProductList } = SavingsApis();
  const router = useRouter();

  useEffect(() => {
    const fetchData = async () => {
      const savingsListData = await getSavingsProductList();
      setSavingsList(savingsListData?.data?.savingsList);
    };

    fetchData();
  }, []);

  return (
    <>
      <section className="w-full p-[1.2rem] flex flex-col gap-[1rem] relative">
        {savingsList?.map((savings, index) => {
          return (
            <FlexBox
              key={`savings-${index}`}
              isDivided={true}
              topChildren={<Title name={savings.name.slice(0, 20) + ' ...'} />}
              bottomChildren={
                <>
                  <div className="flex flex-col gap-[0.5rem]">
                    <ContentRow
                      keyName="이자율"
                      value={`${savings.interest}%`}
                    />
                    <ContentRow
                      keyName="적금 기간"
                      value={`${savings.period}개월`}
                    />
                  </div>
                  <div className="self-end">
                    <TinyButton
                      label="선택하기"
                      handleClick={() =>
                        router.push(
                          `/savings/apply/${savings.savingsUuid}/step1`,
                        )
                      }
                    />
                  </div>
                </>
              }
            />
          );
        })}
      </section>
    </>
  );
}
