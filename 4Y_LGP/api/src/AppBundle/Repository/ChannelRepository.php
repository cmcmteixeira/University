<?php

namespace AppBundle\Repository;

use Doctrine\ODM\MongoDB\DocumentRepository;

/**
 * ChannelRepository
 *
 * This class was generated by the Doctrine ORM. Add your own custom
 * repository methods below.
 */
class ChannelRepository extends DocumentRepository
{
    public function getChannelByName($cname){
        return $this
            ->dm
            ->getRepository('AppBundle:Channel')
            ->findOneBy(['name' => $cname]);
    }


}